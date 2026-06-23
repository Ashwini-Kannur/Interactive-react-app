import * as fs from "fs";
import * as path from "path";
import { runClaude } from "./claude";
import { TestCase, TestRun } from "./types";

function stripCodeFences(text: string): string {
  const trimmed = text.trim();
  const fenceMatch = /^```[a-zA-Z]*\n([\s\S]*?)\n```$/.exec(trimmed);
  return fenceMatch ? fenceMatch[1] : trimmed;
}

export async function generateScriptForTestCase(
  claudeCliPath: string,
  testCase: TestCase,
  targetAppPath: string
): Promise<string> {
  const prompt = [
    "Write a Playwright TypeScript test spec for the following QA test case.",
    "Return only the code for the spec file. Do not include markdown code fences or any explanation.",
    "",
    `Title: ${testCase.title}`,
    `Description: ${testCase.description}`,
    "Steps:",
    ...testCase.steps.map((s, i) => `${i + 1}. ${s}`),
    `Expected Result: ${testCase.expectedResult}`,
  ].join("\n");

  const raw = (await runClaude(claudeCliPath, prompt)) as string;
  const code = stripCodeFences(raw);

  const dir = path.join(targetAppPath, "tests", testCase.module);
  fs.mkdirSync(dir, { recursive: true });
  const filePath = path.join(dir, `${testCase.id}.spec.ts`);
  fs.writeFileSync(filePath, code);

  return filePath;
}

export function recordRun(dataDir: string, run: TestRun): void {
  const file = path.join(dataDir, "results.json");
  const existing: TestRun[] = fs.existsSync(file) ? JSON.parse(fs.readFileSync(file, "utf-8")) : [];
  existing.push(run);
  fs.mkdirSync(dataDir, { recursive: true });
  fs.writeFileSync(file, JSON.stringify(existing, null, 2));
}
