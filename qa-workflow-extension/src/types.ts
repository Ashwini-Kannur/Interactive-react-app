export type TestCaseStatus = "draft" | "refined" | "automated";

export interface TestCase {
  id: string;
  module: string;
  title: string;
  description: string;
  steps: string[];
  expectedResult: string;
  status: TestCaseStatus;
  createdAt: string;
}

export type TestResultStatus = "passed" | "failed" | "skipped";

export interface TestResult {
  testCaseId: string;
  status: TestResultStatus;
  duration?: number;
}

export interface TestRun {
  runId: string;
  timestamp: string;
  module?: string;
  results: TestResult[];
}
