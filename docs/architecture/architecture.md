# 🏗️ Architecture

```mermaid
flowchart LR
%% CLIENTS
    A[Client / CLI / Postman] -->|REST| APIGW[API Gateway / Load Balancer]

%% SERVICES
    subgraph CORE["Core Microservices"]
        direction TB
        CS[Customer Service<br/>REST, SQL]
        VS[Vehicle Service<br/>REST, SQL]
        RS[Reservation Service<br/>REST, SQL, RMQ Producer]
    end

    subgraph ASYNC["Async Processing"]
        direction TB
        BS[Billing Service<br/>RMQ Consumer, SQL]
        NS[Notification Service<br/>RMQ Consumer, WebSocket]
        AN[Analytics Service<br/>RMQ Consumer, SQL]
    end

%% RABBITMQ + DATABASES
    RMQ[RabbitMQ<br/>Event Bus]
    CDB[Postgres: customerdb]
    VDB[Postgres: vehicledb]
    RDB[Postgres: reservationdb]
    BDB[Postgres: billingdb]
    NDB[Postgres: notificationdb]
    ADB[Postgres: analyticsdb]

%% GATEWAY ROUTING
    APIGW --> CS
    APIGW --> VS
    APIGW --> RS
    APIGW --> BS
    APIGW --> NS
    APIGW --> AN

%% REST DEPENDENCIES
    RS -- "REST: validate customer" --> CS
    RS -- "REST: check vehicle availability" --> VS

%% DATABASE LINKS
    CS --> CDB
    VS --> VDB
    RS --> RDB
    BS --> BDB
    NS --> NDB
    AN --> ADB

%% RMQ EVENT FLOWS
    RS -->|publishes: reservation.*| RMQ
    BS -->|publishes: invoice.*| RMQ
    RMQ -->|consumes: reservation.*| BS
    RMQ -->|consumes: reservation.*| NS
    RMQ -->|consumes: reservation.*| AN
    RMQ -->|consumes: reservation.*| VS

%% WEBSOCKET PATH (no braces)
    NS -->|WebSocket /topic/reservations/<customerId>| CLIENTWS[Client WebSocket<br/>Dashboard]

%% ADMIN UTILITIES
    Ops[RabbitMQ Mgmt UI<br/>Docker Compose] --- RMQ

%% STYLING
    classDef svc fill:#f3f4f6,stroke:#333,stroke-width:1px;
    class CS,VS,RS,BS,NS,AN svc;
    classDef db fill:#fef9c3,stroke:#888,stroke-width:1px;
    class CDB,VDB,RDB,BDB,NDB,ADB db;
    classDef bus fill:#e0f2fe,stroke:#0369a1,stroke-width:1px;
    class RMQ bus;

```
## Key notes:
- API Gateway receives client REST calls and routes to the appropriate service.
- Reservation Service is the orchestrator: it performs synchronous REST checks against Customer and Vehicle, persists the reservation, then publishes reservation.* events to RabbitMQ.
- Billing, Notification, Analytics, and Vehicle services consume RabbitMQ events and act asynchronously. Billing may publish invoice.* back to RabbitMQ.
- Notification Service pushes real-time updates to clients via WebSocket channels (no UI required — students can use Postman / wscat / browser devtools).
- Each service owns its own database (database-per-service pattern) to practice bounded contexts and avoid shared-schema coupling.
