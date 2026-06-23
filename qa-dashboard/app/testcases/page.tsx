import { TestCasesTable } from "@/components/testcases-table";
import { getTestCases } from "@/lib/data";

export default async function TestCasesPage() {
  const testCases = await getTestCases();

  return (
    <div className="flex flex-col gap-8">
      <div>
        <h1 className="text-2xl font-semibold">Test Cases</h1>
        <p className="text-muted-foreground">
          All generated test cases, filterable by refinement status.
        </p>
      </div>
      <TestCasesTable testCases={testCases} />
    </div>
  );
}
