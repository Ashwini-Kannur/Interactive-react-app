import fs from "fs";
import path from "path";
import type { TestCase, TestRun } from "./types";

const testCasesDir = path.join(process.cwd(), "data", "testcases");
const resultsPath = path.join(process.cwd(), "data", "results.json");

export function readTestCases(): TestCase[] {
  if (!fs.existsSync(testCasesDir)) {
    // Falls back to the old single-file layout until the extension's
    // one-time migration (triggered by any QA Workflow command) has run.
    const legacyFile = path.join(process.cwd(), "data", "testcases.json");
    if (!fs.existsSync(legacyFile)) return [];
    return JSON.parse(fs.readFileSync(legacyFile, "utf-8"));
  }
  const files = fs.readdirSync(testCasesDir).filter((f) => f.endsWith(".json"));
  const all: TestCase[] = [];
  for (const file of files) {
    all.push(...(JSON.parse(fs.readFileSync(path.join(testCasesDir, file), "utf-8")) as TestCase[]));
  }
  return all;
}

export function readResults(): TestRun[] {
  if (!fs.existsSync(resultsPath)) return [];
  return JSON.parse(fs.readFileSync(resultsPath, "utf-8"));
}