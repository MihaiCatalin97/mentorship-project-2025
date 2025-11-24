# Auth Service Module

## Overview
Initial setup for the **auth-service** responsible for user creation.  
Users are stored in-memory; a default **ADMIN** user is created at application startup.  
All users created via API receive role **USER**. Happy-flow only (no validations).

## Setup Steps
- Created new module: `auth-service` (under `back-end/services`).
- Configured `pom.xml` to inherit from `services` and use Spring Boot.
- Established package structure: `com.project.mentorship.service.auth`.
- Added `AuthServiceApplication` (Spring Boot entry point).
- Implemented layers:
    - `api` → `UserController` (POST `/users`)
    - `api/dto` → `UserDto`
    - `mapper` → `UserMapper` (DTO -> Domain && Domain -> DTO)
    - `domain` → `User`, `Role`
    - `persistance` → `UserRepository` (in-memory, default admin in non-static block)
    - `service` → `UserService` (`create(User)` delegates to repository)

## Build & Integration
- Module included in Maven reactor.
- Build:
  ```bash
  mvn clean install -DskipTests
