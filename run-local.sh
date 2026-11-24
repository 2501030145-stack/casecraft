#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")" && pwd)"
if ! command -v java >/dev/null 2>&1; then echo "Java 21 is required."; exit 1; fi
if ! command -v mvn >/dev/null 2>&1; then echo "Maven 3.9+ is required."; exit 1; fi
if ! command -v npm >/dev/null 2>&1; then echo "Node.js 20+ and npm are required."; exit 1; fi
(cd "$ROOT/backend" && mvn spring-boot:run) &
API_PID=$!
trap 'kill "$API_PID" 2>/dev/null || true' EXIT
(cd "$ROOT/frontend" && npm install && npm run dev)
