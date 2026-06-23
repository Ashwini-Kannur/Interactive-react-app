import { readResults } from "@/lib/data";
import PassRateChart, { PassRateDatum } from "../components/PassRateChart";

export const dynamic = "force-dynamic";

export default function ReportsPage() {
  const runs = readResults();

  const passRateData: PassRateDatum[] = runs.map((run) => {
    const passed = run.results.filter((r) => r.status === "passed").length;
    const passRate = run.results.length === 0 ? 0 : Math.round((passed / run.results.length) * 100);
    return { runId: run.runId, passRate };
  });

  return (
    <div>
      <h1>Reports ({runs.length} runs)</h1>
      <PassRateChart data={passRateData} />
      {runs.map((run) => (
        <div key={run.runId}>
          <h2 className="module-heading">
            {run.runId} &middot; {new Date(run.timestamp).toLocaleString()}
            {run.module ? ` · ${run.module}` : ""}
          </h2>
          <table>
            <thead>
              <tr>
                <th>Test Case</th>
                <th>Status</th>
                <th>Duration (ms)</th>
              </tr>
            </thead>
            <tbody>
              {run.results.map((r) => (
                <tr key={r.testCaseId}>
                  <td>{r.testCaseId}</td>
                  <td>
                    <span className={`badge badge-${r.status}`}>{r.status}</span>
                  </td>
                  <td>{r.duration ?? "—"}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ))}
    </div>
  );
}