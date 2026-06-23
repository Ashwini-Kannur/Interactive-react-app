"use client";

import { useMemo, useState } from "react";
import { Badge } from "@/components/ui/badge";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Tabs, TabsList, TabsTrigger } from "@/components/ui/tabs";
import type { TestCase, TestCaseStatus } from "@/lib/data";

const FILTERS: { label: string; value: TestCaseStatus | "all" }[] = [
  { label: "All", value: "all" },
  { label: "Draft", value: "draft" },
  { label: "Refined", value: "refined" },
  { label: "Automated", value: "automated" },
];

const STATUS_VARIANT: Record<
  TestCaseStatus,
  "outline" | "secondary" | "default"
> = {
  draft: "outline",
  refined: "secondary",
  automated: "default",
};

export function TestCasesTable({ testCases }: { testCases: TestCase[] }) {
  const [filter, setFilter] = useState<TestCaseStatus | "all">("all");

  const filtered = useMemo(
    () =>
      filter === "all"
        ? testCases
        : testCases.filter((tc) => tc.status === filter),
    [testCases, filter]
  );

  return (
    <div className="flex flex-col gap-4">
      <Tabs
        defaultValue="all"
        onValueChange={(value) => setFilter(value as TestCaseStatus | "all")}
      >
        <TabsList>
          {FILTERS.map((f) => (
            <TabsTrigger key={f.value} value={f.value}>
              {f.label}
            </TabsTrigger>
          ))}
        </TabsList>
      </Tabs>

      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>Title</TableHead>
            <TableHead>Requirement</TableHead>
            <TableHead>Status</TableHead>
            <TableHead>Created</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {filtered.map((tc) => (
            <TableRow key={tc.id}>
              <TableCell className="font-mono text-xs">{tc.id}</TableCell>
              <TableCell>{tc.title}</TableCell>
              <TableCell className="text-muted-foreground">
                {tc.requirement}
              </TableCell>
              <TableCell>
                <Badge variant={STATUS_VARIANT[tc.status]}>{tc.status}</Badge>
              </TableCell>
              <TableCell>{tc.createdAt}</TableCell>
            </TableRow>
          ))}
          {filtered.length === 0 && (
            <TableRow>
              <TableCell colSpan={5} className="text-center text-muted-foreground">
                No test cases match this filter.
              </TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
    </div>
  );
}
