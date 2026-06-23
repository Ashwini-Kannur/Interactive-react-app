import { execFile } from "child_process";
import { promisify } from "util";

const execFileAsync = promisify(execFile);

export interface RunClaudeOptions {
  jsonSchema?: object;
  cwd?: string;
}

interface ClaudeResultTurn {
  type: "result";
  is_error: boolean;
  result: string;
  structured_output?: unknown;
}

function parseClaudeOutput(stdout: string): ClaudeResultTurn {
  const parsed = JSON.parse(stdout);
  const turn: ClaudeResultTurn = Array.isArray(parsed) ? parsed[parsed.length - 1] : parsed;
  return turn;
}

export async function runClaude(cliPath: string, prompt: string, opts: RunClaudeOptions = {}): Promise<unknown> {
  const args = ["-p", prompt, "--output-format", "json"];
  if (opts.jsonSchema) {
    args.push("--json-schema", JSON.stringify(opts.jsonSchema));
  }

  const { stdout } = await execFileAsync(cliPath, args, {
    cwd: opts.cwd,
    maxBuffer: 10 * 1024 * 1024,
  });

  const turn = parseClaudeOutput(stdout);
  if (turn.is_error) {
    throw new Error(turn.result || "claude CLI returned an error");
  }

  return opts.jsonSchema ? turn.structured_output : turn.result;
}
