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

export interface PassRatePoint {
  date: string;
  passRate: number;
}

export function PassRateTrendChart({ data }: { data: PassRatePoint[] }) {
  return (
    <ResponsiveContainer width="100%" height={260}>
      <LineChart data={data}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="date" fontSize={12} />
        <YAxis domain={[0, 100]} fontSize={12} unit="%" />
        <Tooltip formatter={(value) => `${value}%`} />
        <Line
          type="monotone"
          dataKey="passRate"
          stroke="#2563eb"
          strokeWidth={2}
          dot={{ r: 4 }}
        />
      </LineChart>
    </ResponsiveContainer>
  );
}
