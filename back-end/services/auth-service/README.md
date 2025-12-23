# auth-service

## Overview
Maven module responsible for user authentication and user management within the system.
Implements a simple in-memory persistence layer for now, focusing on core flows (happy path).
## Structure
- `AuthServiceApplication` — entry point (Spring Boot)
- `UserController` — handles HTTP requests
- `UserService` — business logic for user creation
- `UserRepository` — in-memory persistence
- `UserMapper` — maps between domain and DTOs
- `UserDto`  — data transfer object used for user creation and responses

## Build
```bash
mvn install -DskipTests
```

## API

### POST @ /users

#### Description
Creates a new user in the system.  
This endpoint is responsible for handling user registration.  
The request body is mapped to a domain `User` object and passed to the `UserService` which performs hashing and persistence.

#### Validations
For this story, **only the happy flow is implemented**.  
No input validation or error handling is applied.  
Security rules are not enforced yet.

#### Request Example
```json
{
  "username": "alex",
  "email": "alex@gmail.com",
  "password": "1234"
}
```

#### Response Example
```json
{
  "id": "fd64eb32-6f17-4299-a8bc-6902a0071efe",
  "username": "alex",
  "password": "alex@gmail.com",
  "email": null,
  "role": "USER",
  "createdAt": "2025-11-27T09:23:51.849+00:00",
  "updatedAt": "2025-11-27T09:23:51.849+00:00"
}
```

### Get @ /users/{id}

#### Description
Fetches a user by its ID.
This endpoint retrieves the user through the service and maps it into a DTO.

#### Notes
- Only happy flow implemented
- Returns 200 OK with user data if found
- If the user does not exist, the repository throws UserNotFoundException
- @ResponseStatus(HttpStatus.NOT_FOUND) ensures the API returns 404 instead of 500

#### Request Example
- No request body needed
- Path parameter: `id` (UUID of the user)
- Example: GET at `http://localhost:8082/auth/users/38d4bf7c-1439-496b-b608-55b0fcdbd621`

#### Response Example (200 OK)
```json
{
    "id": "38d4bf7c-1439-496b-b608-55b0fcdbd62e",
    "username": "casiana",
    "password": "$2a$10$HJ1XY8fx2YWMId1d/RbzC.HN1gRQ1Yb9doe..bH5jNUjxZYzWzD66",
    "email": "casiana@gmail.com",
    "role": "USER",
    "createdAt": null,
    "updatedAt": null
}
```
## Added Config package in tests.
Added a config package under src/test/java for test-specific Spring configurations.
Included a TestSecurityConfig class to provide a minimal SecurityFilterChain bean for tests, 
disabling CSRF and allowing all requests.
Also removed spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration from applications.properties 
to ensure test configurations are applied correctly.