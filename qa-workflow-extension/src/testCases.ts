import * as fs from "fs";
import * as path from "path";
import { runClaude } from "./claude";
import { TestCase } from "./types";

function testCasesDir(dataDir: string): string {
  return path.join(dataDir, "testcases");
}

function sanitizeModuleFileName(module: string | undefined): string {
  const trimmed = (module ?? "").replace(/[\\/:*?"<>|]/g, "_").trim();
  return trimmed || "Unsorted";
}

export function moduleFilePath(dataDir: string, module: string): string {
  return path.join(testCasesDir(dataDir), `${sanitizeModuleFileName(module)}.json`);
}

export function readTestCases(dataDir: string): TestCase[] {
  const dir = testCasesDir(dataDir);

  if (!fs.existsSync(dir)) {
    // One-time migration from the old single testcases.json layout.
    const legacyFile = path.join(dataDir, "testcases.json");
    if (!fs.existsSync(legacyFile)) return [];
    const legacy: TestCase[] = JSON.parse(fs.readFileSync(legacyFile, "utf-8"));
    writeTestCases(dataDir, legacy);
    fs.renameSync(legacyFile, `${legacyFile}.bak`);
    return legacy;
  }

  const files = fs.readdirSync(dir).filter((f) => f.endsWith(".json"));
  const all: TestCase[] = [];
  for (const file of files) {
    all.push(...(JSON.parse(fs.readFileSync(path.join(dir, file), "utf-8")) as TestCase[]));
  }
  return all;
}

export function writeTestCases(dataDir: string, cases: TestCase[]): void {
  const dir = testCasesDir(dataDir);
  fs.mkdirSync(dir, { recursive: true });

  const byModule = new Map<string, TestCase[]>();
  for (const tc of cases) {
    const key = tc.module ?? "Unsorted";
    const list = byModule.get(key) ?? [];
    list.push(tc);
    byModule.set(key, list);
  }

  for (const [module, moduleCases] of byModule) {
    const file = path.join(dir, `${sanitizeModuleFileName(module)}.json`);
    fs.writeFileSync(file, JSON.stringify(moduleCases, null, 2));
  }
}

export function nextId(existing: TestCase[]): string {
  const max = existing.reduce((acc, tc) => {
    const match = /^TC-(\d+)$/.exec(tc.id);
    return match ? Math.max(acc, parseInt(match[1], 10)) : acc;
  }, 0);
  return `TC-${String(max + 1).padStart(3, "0")}`;
}

function testCaseSchema() {
  return {
    type: "object",
    properties: {
      testCases: {
        type: "array",
        items: {
          type: "object",
          properties: {
            title: { type: "string" },
            description: { type: "string" },
            steps: { type: "array", items: { type: "string" } },
            expectedResult: { type: "string" },
          },
          required: ["title", "description", "steps", "expectedResult"],
        },
      },
    },
    required: ["testCases"],
  };
}

export interface GenerateTestCasesArgs {
  claudeCliPath: string;
  requirement: string;
  module: string;
  existing: TestCase[];
}

export async function generateTestCases(args: GenerateTestCasesArgs): Promise<TestCase[]> {
  const prompt = `You are a QA engineer. Given the following requirement for the "${args.module}" module, generate a thorough set of QA test cases as structured data.\n\nRequirement:\n${args.requirement}`;

  const output = (await runClaude(args.claudeCliPath, prompt, {
    jsonSchema: testCaseSchema(),
  })) as { testCases: Array<{ title: string; description: string; steps: string[]; expectedResult: string }> };

  const generated = output?.testCases ?? [];
  const result: TestCase[] = [];
  const pool = [...args.existing];

  for (const tc of generated) {
    const id = nextId(pool);
    const testCase: TestCase = {
      id,
      module: args.module,
      title: tc.title,
      description: tc.description,
      steps: tc.steps,
      expectedResult: tc.expectedResult,
      status: "draft",
      createdAt: new Date().toISOString(),
    };
    pool.push(testCase);
    result.push(testCase);
  }

  return result;
}