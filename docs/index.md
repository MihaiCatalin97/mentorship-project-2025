# Documentation

## 🧩 Project Tech Stack

### 🏗️ 1. Core Language and Frameworks

| Area                            | Technology                  | Purpose                                                          |
|---------------------------------|-----------------------------|------------------------------------------------------------------|
| **Language**                    | **Java 21**                 | Modern LTS release with virtual threads and enhanced performance |
| **Framework**                   | **Spring Boot 3.x**         | Rapid microservice development and auto-configuration            |
| **Dependency Injection / Core** | **Spring Framework**        | Core IoC and AOP functionality                                   |
| **REST API**                    | **Spring Web (Spring MVC)** | Exposes REST endpoints and handles HTTP requests                 |
| **WebSocket (optional)**        | Spring WebSocket            | Enables real-time notifications (future milestone)               |
| **Messaging**                   | **Spring AMQP (RabbitMQ)**  | For asynchronous service-to-service events                       |

### 🧠 2. Persistence and Data

| Area                  | Technology                      | Purpose                                                    |
|-----------------------|---------------------------------|------------------------------------------------------------|
| **ORM Layer**         | **Spring Data JPA (Hibernate)** | Simplifies data persistence with entities and repositories |
| **Database**          | **PostgreSQL**                  | Relational database for storing business data              |
| **Schema Management** | **Flyway**                      | Version-controlled database migrations                     |
| **Querying**          | **JPA @Query annotations**      | For writing custom JPQL or native SQL queries              |

### 🧵 3. Inter-Service Communication

| Type               | Technology        | Purpose                                                         |
|--------------------|-------------------|-----------------------------------------------------------------|
| **Synchronous**    | **Feign Clients** | Declarative HTTP clients for microservice-to-microservice calls |
| **Asynchronous**   | **RabbitMQ**      | Event-driven message passing between services                   |
| **Event Payloads** | JSON              | Lightweight and human-readable serialization format             |

### 🔐 4. Security and Config

| Area                               | Technology                              | Purpose                                                |
|------------------------------------|-----------------------------------------|--------------------------------------------------------|
| **Authentication / Authorization** | **Spring Security with JWT-based RBAC** | Role-based access control using signed JWT tokens      |
| **API Contracts**                  | **SpringDoc OpenAPI**                   | Auto-generated REST documentation and client contracts |
| **Input Validation**               | Jakarta Bean Validation                 | Validates incoming request DTOs                        |

### 🧰 5. Cross-Cutting Concerns

| Area                     | Technology                           | Purpose                                       |
|--------------------------|--------------------------------------|-----------------------------------------------|
| **Logging**              | **SLF4J + Logback**                  | Unified logging layer for all services        |
| **Monitoring / Metrics** | **Spring Boot Actuator**             | Health endpoints, metrics, and service status |
| **Testing**              | **JUnit 5, Mockito, Testcontainers** | Unit, integration, and containerized DB tests |

### ⚙️ 6. Development and Tooling

| Area                   | Technology                  | Purpose                                           |
|------------------------|-----------------------------|---------------------------------------------------|
| **Build Tool**         | **Maven**                   | Dependency management and builds                  |
| **Version Control**    | **Git (GitHub)**            | Source code management                            |
| **Project Management** | **Jira**                    | Task and sprint tracking                          |
| **Code Quality**       | **SonarQube**               | Static analysis and code health metrics           |
| **CI/CD**              | **GitHub Actions**          | Automated builds, tests, and deployment pipelines |
| **Containerization**   | **Docker + Docker Compose** | Containerized services and local orchestration    |

### 🧩 7. Optional Enhancements

| Area                        | Technology             | Purpose                                              |
|-----------------------------|------------------------|------------------------------------------------------|
| **API Gateway**             | Spring Cloud Gateway   | Centralized entry point and routing between services |
| **Configuration Server**    | Spring Cloud Config    | Centralized configuration for multiple services      |
| **Tracing / Observability** | OpenTelemetry / Zipkin | Distributed tracing across microservices             |
| **Service Discovery**       | Eureka / Consul        | Dynamic service registration and lookup              |

## 🚀 Future Enhancements

- JWT-based authentication with Spring Security
- API Gateway (Spring Cloud Gateway)
- Config Server for centralized configuration
- Kafka as alternative to RabbitMQ
- GraphQL API layer
- OpenAPI contracts
- Swagger Documentation
- Integration tests with TestContainers

The project contains documentation for the following:

### [Architecture](architecture/architecture.md)

### [Communication between services](communication/communication.md)

### [Data Models](dataModel/dataModel.md)

### [User Flows](flows/flows.md)

### [Services](services/services.md)
