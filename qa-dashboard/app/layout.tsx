import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import Link from "next/link";
import "./globals.css";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "QA Dashboard",
  description: "Test case coverage and execution reporting",
};

const NAV_LINKS = [
  { href: "/", label: "Overview" },
  { href: "/testcases", label: "Test Cases" },
  { href: "/reports", label: "Reports" },
];

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html
      lang="en"
      className={`${geistSans.variable} ${geistMono.variable} h-full antialiased`}
    >
      <body className="min-h-full flex flex-col">
        <header className="border-b">
          <nav className="mx-auto max-w-6xl flex items-center gap-6 px-6 py-4">
            <span className="font-semibold">QA Dashboard</span>
            <div className="flex gap-4 text-sm text-muted-foreground">
              {NAV_LINKS.map((link) => (
                <Link
                  key={link.href}
                  href={link.href}
                  className="hover:text-foreground"
                >
                  {link.label}
                </Link>
              ))}
            </div>
          </nav>
        </header>
        <main className="flex-1 mx-auto w-full max-w-6xl px-6 py-8">
          {children}
        </main>
      </body>
    </html>
  );
}
