"use client";

import {
  Bar,
  BarChart,
  CartesianGrid,
  Legend,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";

export interface RunResultPoint {
  date: string;
  pass: number;
  fail: number;
  skip: number;
}

export function RunResultsChart({ data }: { data: RunResultPoint[] }) {
  return (
    <ResponsiveContainer width="100%" height={260}>
      <BarChart data={data}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="date" fontSize={12} />
        <YAxis allowDecimals={false} fontSize={12} />
        <Tooltip />
        <Legend />
        <Bar dataKey="pass" stackId="a" fill="#16a34a" />
        <Bar dataKey="fail" stackId="a" fill="#dc2626" />
        <Bar dataKey="skip" stackId="a" fill="#a1a1aa" />
      </BarChart>
    </ResponsiveContainer>
  );
}
