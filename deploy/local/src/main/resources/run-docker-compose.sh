#!/bin/bash
# Run Docker Compose from the resources folder

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

docker-compose up --build
