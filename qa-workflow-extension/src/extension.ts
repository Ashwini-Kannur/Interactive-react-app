import * as vscode from "vscode";
import * as path from "path";
import { exec } from "child_process";
import { promisify } from "util";
import { generateTestCases, moduleFilePath, nextId, readTestCases, writeTestCases } from "./testCases";
import { generateScriptForTestCase, recordRun } from "./scripts";
import { TestCase, TestResult, TestRun } from "./types";

const execAsync = promisify(exec);
const output = vscode.window.createOutputChannel("QA Workflow");

function wrap(fn: () => Promise<void>) {
  return async () => {
    try {
      await fn();
    } catch (err) {
      const detail = err instanceof Error ? err.stack ?? err.message : String(err);
      output.appendLine(detail);
      output.show(true);
      vscode.window.showErrorMessage(`QA Workflow: ${err instanceof Error ? err.message : String(err)}`);
    }
  };
}

function resolvePath(configValue: string): string {
  if (path.isAbsolute(configValue)) return configValue;
  const folders = vscode.workspace.workspaceFolders;
  if (!folders || folders.length === 0) {
    throw new Error("Open a workspace folder before running QA Workflow commands.");
  }
  return path.join(folders[0].uri.fsPath, configValue);
}

function config() {
  const cfg = vscode.workspace.getConfiguration("qaWorkflow");
  return {
    dataDir: resolvePath(cfg.get<string>("dashboardDataPath", "qa-dashboard/data")),
    targetAppPath: resolvePath(cfg.get<string>("targetAppPath", "target-app")),
    claudeCliPath: cfg.get<string>("claudeCliPath", "claude"),
  };
}

function settleQuickInput(): Promise<void> {
  return new Promise((resolve) => setTimeout(resolve, 150));
}

function moduleNames(cases: TestCase[]): string[] {
  return [...new Set(cases.map((tc) => tc.module).filter((m): m is string => Boolean(m)))];
}

async function pickModule(existingModules: string[]): Promise<string | undefined> {
  const NEW_MODULE = "New module…";
  const choice = await vscode.window.showQuickPick([...existingModules, NEW_MODULE], {
    placeHolder: "Select a module",
  });
  if (!choice) return undefined;
  if (choice !== NEW_MODULE) return choice;
  await settleQuickInput();
  return vscode.window.showInputBox({ prompt: "New module name" });
}

async function cmdGenerateTestCases() {
  const { dataDir, claudeCliPath } = config();

  // The command was just selected from the Command Palette, itself a
  // QuickPick that's still closing — give it a moment before opening ours.
  await settleQuickInput();

  const editor = vscode.window.activeTextEditor;
  const selectedText = editor && !editor.selection.isEmpty ? editor.document.getText(editor.selection) : undefined;

  const requirement =
    selectedText ??
    (await vscode.window.showInputBox({
      prompt: "Describe the requirement to generate test cases for",
      ignoreFocusOut: true,
    }));
  if (!requirement) return;

  await settleQuickInput();

  const existing = readTestCases(dataDir);
  const module = await pickModule(moduleNames(existing));
  if (!module) return;

  await vscode.window.withProgress(
    { location: vscode.ProgressLocation.Notification, title: `Generating test cases for ${module}…` },
    async () => {
      const newCases = await generateTestCases({ claudeCliPath, requirement, module, existing });
      writeTestCases(dataDir, [...existing, ...newCases]);
      vscode.window.showInformationMessage(`Generated ${newCases.length} test case(s) for ${module}.`);
     const doc = await vscode.workspace.openTextDocument(moduleFilePath(dataDir, module));
      await vscode.window.showTextDocument(doc);
    }
  );
}

async function cmdMarkRefined() {
  await settleQuickInput();

  const { dataDir } = config();
  const cases = readTestCases(dataDir);
  const draftCases = cases.filter((tc) => tc.status === "draft");

  if (draftCases.length === 0) {
    vscode.window.showInformationMessage("No draft test cases to refine.");
    return;
  }

  const picks = await vscode.window.showQuickPick(
    draftCases.map((tc) => ({ label: `${tc.id} — ${tc.title}`, description: tc.module, testCase: tc })),
    { canPickMany: true, placeHolder: "Select test cases to mark as refined" }
  );
  if (!picks || picks.length === 0) return;

  const selectedIds = new Set(picks.map((p) => p.testCase.id));
  const updated = cases.map((tc): TestCase => (selectedIds.has(tc.id) ? { ...tc, status: "refined" } : tc));
  writeTestCases(dataDir, updated);
  vscode.window.showInformationMessage(`Marked ${selectedIds.size} test case(s) as refined.`);
}

async function cmdGenerateScripts() {
  await settleQuickInput();

  const { dataDir, targetAppPath, claudeCliPath } = config();
  const cases = readTestCases(dataDir);
  const refinedCases = cases.filter((tc) => tc.status === "refined");

  if (refinedCases.length === 0) {
    vscode.window.showInformationMessage("No refined test cases available. Run 'Mark Test Cases as Refined' first.");
    return;
  }

  const module = await vscode.window.showQuickPick(moduleNames(refinedCases), {
    placeHolder: "Select a module to generate scripts for",
  });
  if (!module) return;

  const targets = refinedCases.filter((tc) => tc.module === module);

  await vscode.window.withProgress(
    { location: vscode.ProgressLocation.Notification, title: `Generating scripts for ${module}…` },
    async (progress) => {
      const automatedIds = new Set<string>();
      for (const [i, tc] of targets.entries()) {
        progress.report({ message: `${tc.id} (${i + 1}/${targets.length})` });
        try {
          await generateScriptForTestCase(claudeCliPath, tc, targetAppPath);
          automatedIds.add(tc.id);
        } catch (err) {
          vscode.window.showErrorMessage(`Failed to generate script for ${tc.id}: ${(err as Error).message}`);
        }
      }
      const updated = cases.map((tc): TestCase => (automatedIds.has(tc.id) ? { ...tc, status: "automated" } : tc));
      writeTestCases(dataDir, updated);
      vscode.window.showInformationMessage(`Generated ${automatedIds.size} script(s) for ${module}.`);
    }
  );
}

async function cmdRunTests() {
  const { dataDir, targetAppPath } = config();

  await vscode.window.withProgress(
    { location: vscode.ProgressLocation.Notification, title: "Running Playwright tests…" },
    async () => {
      let stdout: string;
      try {
        const res = await execAsync("npx playwright test --reporter=json", {
          cwd: targetAppPath,
          maxBuffer: 50 * 1024 * 1024,
        });
        stdout = res.stdout;
      } catch (err) {
        const execErr = err as { stdout?: string; message: string };
        if (!execErr.stdout) {
          vscode.window.showErrorMessage(`Failed to run Playwright tests: ${execErr.message}`);
          return;
        }
        stdout = execErr.stdout;
      }

      let report: any;
      try {
        report = JSON.parse(stdout);
      } catch {
        vscode.window.showErrorMessage("Could not parse Playwright JSON report.");
        return;
      }

      const results: TestResult[] = [];
      for (const suite of report.suites ?? []) {
        collectSpecResults(suite, results);
      }

      const run: TestRun = {
        runId: `RUN-${Date.now()}`,
        timestamp: new Date().toISOString(),
        results,
      };
      recordRun(dataDir, run);
      vscode.window.showInformationMessage(`Recorded run ${run.runId} with ${results.length} result(s).`);
    }
  );
}

function collectSpecResults(suite: any, out: TestResult[]): void {
  for (const spec of suite.specs ?? []) {
    const fileName = path.basename(spec.file ?? "");
    const match = /^(TC-\d+)\.spec\.ts$/.exec(fileName);
    if (!match) continue;
    const testCaseId = match[1];
    const allTests = (spec.tests ?? []).flatMap((t: any) => t.results ?? []);
    const passed = allTests.length > 0 && allTests.every((r: any) => r.status === "passed");
    const skipped = allTests.length > 0 && allTests.every((r: any) => r.status === "skipped");
    const duration = allTests.reduce((sum: number, r: any) => sum + (r.duration ?? 0), 0);
    out.push({
      testCaseId,
      status: skipped ? "skipped" : passed ? "passed" : "failed",
      duration,
    });
  }
  for (const child of suite.suites ?? []) {
    collectSpecResults(child, out);
  }
}

export function activate(context: vscode.ExtensionContext) {
  context.subscriptions.push(
    vscode.commands.registerCommand("qaWorkflow.generateTestCases", wrap(cmdGenerateTestCases)),
    vscode.commands.registerCommand("qaWorkflow.markRefined", wrap(cmdMarkRefined)),
    vscode.commands.registerCommand("qaWorkflow.generateScripts", wrap(cmdGenerateScripts)),
    vscode.commands.registerCommand("qaWorkflow.runTests", wrap(cmdRunTests))
  );
}

export function deactivate() {}