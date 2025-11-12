# 🏗️ Architecture

# System Diagrams

## Flowchart

```mermaid
flowchart LR
%% CLIENTS
  A[Client / CLI / Postman] -->|REST| APIGW[API Gateway / Load Balancer]
  A -->|WebSocket wss://api/ws/...| APIGW

%% AUTH SERVICE
  subgraph AUTHG["Auth Service Group"]
    direction LR
    AUTH[Auth Service<br/>REST, JWT RBAC]
    ADB[(authdb)]
    AUTH --- ADB
  end

%% CORE SERVICES
  subgraph CORE["Core Microservices"]
    direction TB

    subgraph CSG["Customer Service Group"]
      direction LR
      CS[Customer Service<br/>REST, SQL]
      CDB[(customerdb)]
      CS --- CDB
    end

    subgraph VSG["Vehicle Service Group"]
      direction LR
      VS[Vehicle Service<br/>REST, SQL]
      VDB[(vehicledb)]
      VS --- VDB
    end

    subgraph RSG["Reservation Service Group"]
      direction LR
      RS[Reservation Service<br/>REST, SQL, RMQ Producer]
      RDB[(reservationdb)]
      RS --- RDB
    end
  end

%% ASYNC SERVICES
  subgraph ASYNC["Async Processing"]
    direction TB

    subgraph BSG["Billing Service Group"]
      direction LR
      BS[Billing Service<br/>RMQ Consumer, SQL, RMQ Producer]
      BDB[(billingdb)]
      BS --- BDB
    end

    subgraph NSG["Notification Service Group"]
      direction LR
      NS[Notification Service<br/>RMQ Consumer, WebSocket]
      NDB[(notificationdb)]
      NS --- NDB
    end

    subgraph ANG["Analytics Service Group"]
      direction LR
      AN[Analytics Service<br/>RMQ Consumer, SQL]
      ADB2[(analyticsdb)]
      AN --- ADB2
    end
  end

%% RABBITMQ
  RMQ[(RabbitMQ<br/>Event Bus)]

%% GATEWAY ROUTING
  APIGW --> AUTH
  APIGW --> CS
  APIGW --> VS
  APIGW --> RS
  APIGW --> BS
  APIGW --> NS
  APIGW --> AN

%% REST DEPENDENCIES
  RS -- " REST: validate customer " --> CS
  RS -- " REST: check vehicle availability " --> VS

%% RMQ EVENT FLOWS
  RS -->|publishes: reservation .*| RMQ
  BS -->|publishes: invoice .*| RMQ
  RMQ -->|consumes: reservation .*| BS
  RMQ -->|consumes: reservation .*| NS
  RMQ -->|consumes: reservation .*| AN
  RMQ -->|consumes: reservation .*| VS
  RMQ -->|consumes: invoice .*| NS
  RMQ -->|consumes: invoice .*| AN

%% WEBSOCKET PATH THROUGH GATEWAY
  APIGW -->|WebSocket proxy| NS

%% ADMIN UTILITIES
  Ops[RabbitMQ Mgmt UI<br/>Docker Compose] --- RMQ

%% STYLE
  classDef bus fill: #e0f2fe, stroke: #0369a1, stroke-width: 1px;
  class RMQ bus;

```

## C4 Model Overview

### C1 — System Context Diagram

```mermaid
graph TB
    title["Car Reservation Platform - System Context"]
    User[End User / Customer<br/>via CLI or Postman]
    System[Car Reservation Platform<br/>Backend Services]
    ExtPayment[(External Payment Gateway)]
    ExtEmail[(External Email Provider)]
    User -->|creates reservation, checks invoices| System
    System -->|charges credit card| ExtPayment
    System -->|sends email notifications| ExtEmail
```

### C2 - Container Diagram

```mermaid
flowchart LR
  subgraph USER["User"]
    U[Customer / Operator]
  end

  subgraph SYSTEM["Car Reservation Platform"]
    APIGW[API Gateway / Load Balancer]

    subgraph CORE["Core Microservices"]
      direction TB
      AUTH[Auth Service<br/>REST, JWT RBAC]
      CS[Customer Service<br/>REST, SQL]
      VS[Vehicle Service<br/>REST, SQL]
      RS[Reservation Service<br/>REST, SQL, RMQ Producer]
    end

    subgraph ASYNC["Async Microservices"]
      direction TB
      BS[Billing Service<br/>RMQ Consumer, SQL, RMQ Producer]
      NS[Notification Service<br/>RMQ Consumer, WebSocket]
      AN[Analytics Service<br/>RMQ Consumer, SQL]
    end

    RMQ[(RabbitMQ Event Bus)]
  end

  subgraph STORAGE["Databases"]
    direction LR
    ADB[(authdb)]
    CDB[(customerdb)]
    VDB[(vehicledb)]
    RDB[(reservationdb)]
    BDB[(billingdb)]
    NDB[(notificationdb)]
    ANDB[(analyticsdb)]
  end

  subgraph EXTERNAL["External Systems"]
    PAY[(Payment Gateway)]
    MAIL[(Email Service)]
  end

%% FLOWS
  U -->|REST / WebSocket| APIGW
  APIGW --> AUTH
  APIGW --> CS
  APIGW --> VS
  APIGW --> RS
  APIGW --> BS
  APIGW --> NS
  APIGW --> AN
  APIGW -->|WebSocket proxy| NS
%% DB LINKS
  AUTH --> ADB
  CS --> CDB
  VS --> VDB
  RS --> RDB
  BS --> BDB
  NS --> NDB
  AN --> ANDB
%% RMQ LINKS
  RS -->|reservation . *| RMQ
  BS -->|invoice . *| RMQ
  RMQ --> BS
  RMQ --> NS
  RMQ --> AN
  RMQ --> VS
%% EXTERNAL LINKS
  BS --> PAY
  NS --> MAIL
```

### C3 — Component Diagram (Reservation Service)

```mermaid
flowchart TB
    subgraph RS["Reservation Service"]
        direction TB
        API[REST Controller<br/>handles /reservations endpoints]
        SVC[Reservation Service Layer<br/>business logic]
        REPO[Reservation Repository<br/>JPA / SQL access]
        RMQPROD[Event Publisher<br/>sends reservation.* to RabbitMQ]
    end

    subgraph DB["Reservation Database"]
        RDB[(reservationdb)]
    end

    subgraph EXT["External Dependencies"]
        CSCALL[Customer Service<br/>REST client]
        VSCALL[Vehicle Service<br/>REST client]
    end

%% FLOWS
    API --> SVC
    SVC --> REPO
    SVC --> RMQPROD
    SVC --> CSCALL
    SVC --> VSCALL
    REPO --> RDB
```

---

# 🧩 N-Layer Architecture (inside each microservice)
```mermaid
flowchart TB
  %% LAYERS
  subgraph PRES["Presentation Layer"]
    direction TB
    CTRL[Controller<br/>REST endpoints<br/>Spring RestController]
  end

  subgraph BUS["Business Layer"]
    direction TB
    SVC[Service<br/>business logic<br/>Spring Service]
  end

  subgraph PERS["Persistence Layer"]
    direction TB
    REPO[Repository<br/>data access<br/>Spring Data JPA]
  end

  subgraph DATA["Data Source Layer"]
    direction TB
    DB[[Database]]
  end

  %% FLOWS
  CTRL --> SVC
  SVC --> REPO
  REPO --> DB

  %% OPTIONAL CROSS-CUTTING
  subgraph CROSS["Cross-Cutting Concerns"]
    direction TB
    LOG[Logging and Monitoring]
    SEC[Security and Authentication]
    CFG[Configuration and Properties]
  end

  LOG -.-> CTRL
  SEC -.-> CTRL
  CFG -.-> SVC
```

```mermaid
flowchart TB
  subgraph ROOT["com.example.<service_name>"]
    direction TB

    subgraph CTRL["controller package"]
      direction TB
      C1[ReservationController]
      C2[ExceptionHandler]
    end

    subgraph SVC["service package"]
      direction TB
      S1[ReservationService]
      S2[CustomerClientService]
    end

    subgraph REPO["repository package"]
      direction TB
      R1[ReservationRepository]
    end

    subgraph MOD["model package"]
      direction TB
      M1[ReservationEntity]
      M2[CustomerDto]
      M3[VehicleDto]
    end

    subgraph CONF["config package"]
      direction TB
      CF1[ApplicationConfig]
      CF2[RabbitMQConfig]
      CF3[SecurityConfig]
    end

    subgraph UTIL["util package"]
      direction TB
      U1[MapperUtil]
      U2[DateUtil]
    end
  end

%% RELATIONSHIPS
  C1 --> S1
  S1 --> R1
  R1 --> M1
  S1 --> CF2
  C1 --> CF3
  S1 --> U1
  U1 --> M1
```

### Entity Creation Sequence Diagram

```mermaid
sequenceDiagram
    participant CLIENT as Client
    participant CONTROLLER as Controller
    participant MAPPER as Mapper
    participant SERVICE as Service
    participant REPOSITORY as Repository
    
    CLIENT ->> CONTROLLER: HTTP Request
    CONTROLLER ->> CONTROLLER: Validates DTO
    CONTROLLER ->> MAPPER: DTO
    MAPPER ->> CONTROLLER: Domain Object
    CONTROLLER ->> SERVICE: Domain Object
    SERVICE ->> SERVICE: Validates Domain Object
    SERVICE ->> REPOSITORY: Domain Object
    REPOSITORY ->> REPOSITORY: Stores Domain Object
    REPOSITORY ->> SERVICE: returns Domain Object
    SERVICE ->> CONTROLLER: returns Domain Object
    CONTROLLER ->> MAPPER: Domain Object
    MAPPER ->> CONTROLLER: DTO
    CONTROLLER ->> CLIENT: HTTP Response
```

## ✅ Layer Responsibilities

| Layer                      | Package          | Purpose                                              | Example classes                               |
|----------------------------|------------------|------------------------------------------------------|-----------------------------------------------|
| **Presentation Layer**     | `controller`     | Exposes REST endpoints, handles input/output mapping | `ReservationController`, `ExceptionHandler`   |
| **Business Layer**         | `service`        | Implements business logic and integration            | `ReservationService`, `CustomerClientService` |
| **Persistence Layer**      | `repository`     | Handles database access using JPA                    | `ReservationRepository`                       |
| **Domain / Data Models**   | `model`          | Contains JPA entities and DTOs                       | `ReservationEntity`, `CustomerDto`            |
| **Cross-Cutting Concerns** | `config`, `util` | Shared configuration, utilities, mapping helpers     | `RabbitMQConfig`, `MapperUtil`                |

# Key notes:

- API Gateway receives client REST calls and routes to the appropriate service.
- Reservation Service is the orchestrator: it performs synchronous REST checks against Customer and Vehicle, persists
  the reservation, then publishes reservation.* events to RabbitMQ.
- Billing, Notification, Analytics, and Vehicle services consume RabbitMQ events and act asynchronously. Billing may
  publish invoice.* back to RabbitMQ.
- Notification Service pushes real-time updates to clients via WebSocket channels (no UI required — students can use
  Postman / wscat / browser devtools).
- Each service owns its own database (database-per-service pattern) to practice bounded contexts and avoid shared-schema
  coupling.
