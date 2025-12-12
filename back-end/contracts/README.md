# Contracts Module – OpenAPI README

This module defines and generates OpenAPI contracts for all microservices.
The purpose is to keep endpoints and DTOs consistent across services by generating shared Java classes from YAML contracts.


## Structure
- `com.project.mentorship.back-end.contracts` - source code
- `pom.xml` - maven build configuration
- `README.md` - project documentation


```python
    contracts
     └── src/main/resources
          ├── analytcis-service
          │     ├── statistics-spec.yml
          │     └── statistics-schemas.yml
          ├── auth-service
          │     ├── user-spec.yml
          │     └── user-schemas.yml
          └── ...
```

## File Roles

- spec.yml → Defines the API endpoints (paths, params, responses).
- schemas.yml → Defines the DTO models used by those endpoints.


## Plugin Used

1. `openapi-generator-maven-plugin`

Used to:
* read spec and schemas YAML files
* generate Java classes (API interfaces, DTOs, delegates)
* place generated code under:  ```target/generated-sources/openapi ```


This ensures all services use the same automatically generated API model.

2. `maven-jar-plugin`

Used to:

* package each service's contracts into separate JARs (via classifiers)
* allow consumers to import only the contracts they need

Example consumer dependency:

  ```
  <dependency>
    <groupId>com.project.mentorship</groupId>
    <artifactId>contracts</artifactId>
    <classifier>auth</classifier>
    <version>${project.version}</version>
 </dependency>
  ```
