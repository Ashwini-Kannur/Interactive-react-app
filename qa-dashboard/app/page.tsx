import { readTestCases } from "@/lib/data";
import StatusChart, { StatusChartDatum } from "./components/StatusChart";
import type { TestCase, TestCaseStatus } from "@/lib/types";

export const dynamic = "force-dynamic";

function groupByModule(cases: TestCase[]): Record<string, TestCase[]> {
  return cases.reduce<Record<string, TestCase[]>>((acc, tc) => {
    (acc[tc.module] ??= []).push(tc);
    return acc;
  }, {});
}

function toChartData(grouped: Record<string, TestCase[]>): StatusChartDatum[] {
  return Object.entries(grouped).map(([module, cases]) => {
    const count = (status: TestCaseStatus) => cases.filter((c) => c.status === status).length;
    return { module, draft: count("draft"), refined: count("refined"), automated: count("automated") };
  });
}

export default function TestCasesPage() {
  const cases = readTestCases();
  const grouped = groupByModule(cases);

  return (
    <div>
      <h1>Test Cases ({cases.length})</h1>
      <StatusChart data={toChartData(grouped)} />
      {Object.entries(grouped).map(([module, moduleCases]) => (
        <div key={module}>
          <h2 className="module-heading">{module}</h2>
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Status</th>
                <th>Created</th>
              </tr>
            </thead>
            <tbody>
              {moduleCases.map((tc) => (
                <tr key={tc.id}>
                  <td>{tc.id}</td>
                  <td>{tc.title}</td>
                  <td>
                    <span className={`badge badge-${tc.status}`}>{tc.status}</span>
                  </td>
                  <td>{new Date(tc.createdAt).toLocaleDateString()}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ))}
    </div>
  );
}