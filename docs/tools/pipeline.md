# CI/CD Pipelines Overview

This document explains how the project’s automated pipelines work, what actions they perform, and when they are triggered.

---

## 1. Overview

The project uses **GitHub Actions** for Continuous Integration (CI).  
Two pipelines are defined under `.github/workflows`:

- **main.yml** – runs on pushes to the `main` branch (after merge)
- **pull_request.yml** – runs on pull requests targeting the `main` branch (before merge)

Both pipelines ensure that the project builds correctly using **Maven** with **Java 21**.

---

## 2. Pipeline: `main.yml`

**Purpose:**  
To validate that the code in the `main` branch compiles successfully after changes are merged.

After a successful build on `main`, the pipeline now also re-triggers all active Pull Request pipelines so they are re-validated against the latest commit on `main`.

This is done by:
- Installing the GitHub CLI (if needed)
- Listing all open PRs for the repository
- Sending a `repository_dispatch` event (`recheck-pr`) for each open PR, which causes the PR pipeline to run again

Example step:

```yaml
- name: Trigger PR rechecks
  env:
    GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
    REPO: ${{ github.repository }}
  run: |
    prs=$(gh pr list --repo "$REPO" --state open --json number --jq '.[].number')

    echo "Found PRs: $prs"
    for pr in $prs; do
      echo "Triggering recheck for PR #$pr"
      gh api "repos/$REPO/dispatches" \
        -f event_type="recheck-pr" \
        -f client_payload="{\"pr_number\": $pr}"
    done
```

## 3. Pull Request Pipeline: `pull_request.yml`

**Purpose:**
To validate that a non-draft pull request targeting the `main` branch compiles successfully before merging.

Before running any build or analysis steps, the PR pipeline now ensures that the PR branch is rebased on top of the latest `main`. This guarantees that the validation always runs against the current state of `main`, not an older snapshot.

Key steps:

```yaml
- name: Fetch latest main
  run: git fetch origin main

- name: Rebase PR branch with latest main
  run: git rebase origin/main
```

**Trigger:**
```yaml
on:
  pull_request:
    branches:
      - main
    types:
      - opened
      - synchronize
      - reopened
      - ready_for_review

  # Allows the main pipeline to retrigger PR validation after a merge into main
  repository_dispatch:
    types: [recheck-pr]
```