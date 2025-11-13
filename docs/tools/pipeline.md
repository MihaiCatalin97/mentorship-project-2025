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


## 3. Pull Request Pipeline: `pull_request.yml`

**Purpose:**
To validate that a draft pull request targeting the `main` branch compiles successfully before merging.


**Trigger:**
```yaml
on:
  push:
    branches:
        - main
