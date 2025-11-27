# auth-service

## Overview
Maven module responsible for user authentication and user management.

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

### Response Example
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
