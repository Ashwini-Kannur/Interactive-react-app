"use client";

import {
  CartesianGrid,
  Line,
  LineChart,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";

export interface DurationPoint {
  date: string;
  avgDurationMs: number;
}

export function DurationTrendChart({ data }: { data: DurationPoint[] }) {
  return (
    <ResponsiveContainer width="100%" height={260}>
      <LineChart data={data}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="date" fontSize={12} />
        <YAxis fontSize={12} unit="ms" />
        <Tooltip formatter={(value) => `${value}ms`} />
        <Line
          type="monotone"
          dataKey="avgDurationMs"
          stroke="#9333ea"
          strokeWidth={2}
          dot={{ r: 4 }}
        />
      </LineChart>
    </ResponsiveContainer>
  );
}
