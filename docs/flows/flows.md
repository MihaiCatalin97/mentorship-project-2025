# 🔁 Typical Flows

## 🧩 Flow 1 — Create Reservation

```mermaid
sequenceDiagram
    participant CLIENT as API Client
    participant GATEWAY as API Gateway
    participant RES as Reservation Service
    participant CUS as Customer Service
    participant VEH as Vehicle Service
    participant RMQ as RabbitMQ
    participant INV as Invoice Service
    participant DBR as Reservation DB
    participant DBI as Invoice DB
    CLIENT ->> GATEWAY: POST /reservations<br/>JWT token + reservation details
    GATEWAY ->> RES: Forward request with validated JWT
    RES ->> CUS: GET /customers/<id> (Feign)
    CUS -->> RES: Customer details
    RES ->> VEH: GET /vehicles/<id> (Feign)
    VEH -->> RES: Vehicle details
    RES ->> DBR: Save reservation (JPA + Flyway)
    DBR -->> RES: Success
    RES ->> RMQ: Publish "ReservationCreated" event
    RMQ -->> INV: Deliver event
    INV ->> DBI: Create invoice for reservation
    DBI -->> INV: Success
    INV -->> RES: Acknowledge invoice created
    RES -->> GATEWAY: Return 201 Created
    GATEWAY -->> CLIENT: Response with reservation and invoice info
```

### 🧠 Flow Description

| Step | Action                                             | Notes                             |
|------|----------------------------------------------------|-----------------------------------|
| 1    | Client sends reservation request with JWT          | Gateway authenticates token       |
| 2    | Reservation Service validates customer and vehicle | Uses Feign Clients                |
| 3    | Reservation saved via JPA                          | Flyway ensures schema consistency |
| 4    | Event published via RabbitMQ                       | Asynchronous message              |
| 5    | Invoice Service consumes event                     | Generates and stores invoice      |
| 6    | Final response returned to client                  | Includes confirmation data        |

## 🧩 Flow 2 — Cancel Reservation

```mermaid
sequenceDiagram
    participant CLIENT as API Client
    participant GATEWAY as API Gateway
    participant RES as Reservation Service
    participant RMQ as RabbitMQ
    participant INV as Invoice Service
    participant DBR as Reservation DB
    participant DBI as Invoice DB
    CLIENT ->> GATEWAY: DELETE /reservations/<id><br/>JWT token
    GATEWAY ->> RES: Forward cancel request with validated JWT
    RES ->> DBR: Find reservation by id
    DBR -->> RES: Return reservation
    RES ->> DBR: Update reservation status to "cancelled"
    DBR -->> RES: Success
    RES ->> RMQ: Publish "ReservationCancelled" event
    RMQ -->> INV: Deliver event
    INV ->> DBI: Update related invoice (mark as void or refunded)
    DBI -->> INV: Success
    INV -->> RES: Invoice updated
    RES -->> GATEWAY: Return 200 OK
    GATEWAY -->> CLIENT: Response "Reservation cancelled successfully"
```

### 🧠 Flow Description

| Step | Action                                           | Notes                       |
|------|--------------------------------------------------|-----------------------------|
| 1    | Client requests cancellation via DELETE endpoint | JWT validated by Gateway    |
| 2    | Reservation Service updates DB                   | Marks record as *cancelled* |
| 3    | Event published via RabbitMQ                     | Asynchronous propagation    |
| 4    | Invoice Service consumes event                   | Adjusts invoice accordingly |
| 5    | Response returned to client                      | Indicates success           |

## 🧩 Flow 3 — List Reservations for Customer

```mermaid
sequenceDiagram
    participant CLIENT as API Client
    participant GATEWAY as API Gateway
    participant RES as Reservation Service
    participant DBR as Reservation DB
    CLIENT ->> GATEWAY: GET /reservations/customer/<customerId><br/>JWT token
    GATEWAY ->> RES: Forward request with validated JWT
    RES ->> DBR: SELECT * FROM reservations WHERE customer_id = <customerId>
    DBR -->> RES: List of reservations
    RES -->> GATEWAY: Return 200 OK with reservations
    GATEWAY -->> CLIENT: Response JSON array of reservations
```

### 🧠 Flow Description

| Step | Action                             | Notes                            |
|------|------------------------------------|----------------------------------|
| 1    | Client requests their reservations | JWT validated by Gateway         |
| 2    | Reservation Service queries DB     | Uses JPA repository or @Query    |
| 3    | Data returned as DTOs              | Avoids exposing entity internals |
| 4    | Client receives result             | Sorted and filtered list         |

## 🧩 Flow 4 — Authentication and Authorization (JWT RBAC)

```mermaid
sequenceDiagram
    participant CLIENT as API Client
    participant AUTH as Auth Service
    participant GATEWAY as API Gateway
    participant RES as Reservation Service
    CLIENT ->> AUTH: POST /auth/login<br/>username + password
    AUTH ->> AUTH: Validate credentials (Spring Security + DB)
    AUTH -->> CLIENT: Return JWT access token + refresh token
    CLIENT ->> GATEWAY: GET /reservations<br/>Authorization: Bearer <token>
    GATEWAY ->> GATEWAY: Validate JWT signature and expiration
    GATEWAY ->> GATEWAY: Check roles and permissions (RBAC)
    GATEWAY ->> RES: Forward request with user context
    RES ->> RES: Verify token claims (user id, role)
    RES -->> GATEWAY: Return 200 OK with reservation data
    GATEWAY -->> CLIENT: Response with authorized data
```

### 🧠 Flow Description

| Step | Action                                                  | Notes                                           |
|------|---------------------------------------------------------|-------------------------------------------------|
| 1    | Client sends login credentials to Auth Service          | Typically username and password                 |
| 2    | Auth Service validates and issues JWT                   | Signed with private key, includes role claims   |
| 3    | Client stores and attaches token to requests            | `Authorization: Bearer <token>`                 |
| 4    | API Gateway validates token                             | Signature, expiry, and roles (RBAC enforcement) |
| 5    | Request forwarded with user context                     | Gateway may add headers like `X-User-Id`        |
| 6    | Reservation Service performs fine-grained authorization | Checks user ownership or roles                  |
| 7    | Authorized data returned                                | Client receives only allowed information        |

### ✅ Security Design Summary

| Component               | Responsibility                                |
|-------------------------|-----------------------------------------------|
| **Auth Service**        | Issues and refreshes JWT tokens               |
| **API Gateway**         | Validates tokens and enforces RBAC policies   |
| **Downstream Services** | Perform domain-specific authorization checks  |
| **JWT Tokens**          | Contain user ID, roles, expiry, and signature |

## 🧩 Flow 5 — Real-Time Reservation Notification (WebSocket)

```mermaid
sequenceDiagram
    participant CLIENT as API Client
    participant GATEWAY as API Gateway
    participant RES as Reservation Service
    participant WS as WebSocket Server
    participant INV as Invoice Service
    participant RMQ as RabbitMQ
    CLIENT ->> GATEWAY: Connect via WebSocket /ws/notifications<br/>JWT token
    GATEWAY ->> WS: Establish WS session after token validation
    RES ->> RMQ: Publish "ReservationConfirmed" event
    RMQ -->> INV: Deliver event to Invoice Service
    INV ->> RMQ: Publish "InvoiceCreated" event
    RMQ -->> RES: Deliver event to Reservation Service
    RES ->> WS: Send message "Reservation confirmed" to user channel
    WS -->> CLIENT: Push notification over WebSocket
```

### 🧠 Flow Description

| Step | Action                                     | Notes                            |
|------|--------------------------------------------|----------------------------------|
| 1    | Client connects to WebSocket endpoint      | JWT validated at connection      |
| 2    | Reservation confirmed triggers event       | Sent asynchronously via RabbitMQ |
| 3    | Invoice Service processes event            | Creates invoice and emits update |
| 4    | Reservation Service receives invoice event | Updates reservation status       |
| 5    | WebSocket notification sent to client      | Real-time confirmation           |

## 🧩 Flow 6 — Invoice Event Handling (Asynchronous via RabbitMQ)

```mermaid
sequenceDiagram
    participant RES as Reservation Service
    participant RMQ as RabbitMQ
    participant INV as Invoice Service
    participant DBI as Invoice DB
    RES ->> RMQ: Publish "ReservationCreated" event
    RMQ -->> INV: Deliver message to queue
    INV ->> INV: Consume message (Spring AMQP Listener)
    INV ->> DBI: Insert new invoice record
    DBI -->> INV: Save successful
    INV ->> RMQ: Publish "InvoiceCreated" event
    RMQ -->> RES: Deliver back for reservation update
    RES ->> RES: Update reservation status to "confirmed"
```

### 🧠 Flow Description

| Step | Action                                | Notes                                  |
|------|---------------------------------------|----------------------------------------|
| 1    | Reservation Service emits event       | Asynchronous communication             |
| 2    | RabbitMQ routes event                 | Queue-based delivery                   |
| 3    | Invoice Service consumes it           | Generates and stores invoice           |
| 4    | Invoice Service emits follow-up event | Enables reactive update on Reservation |
| 5    | Reservation Service updates DB        | Marks reservation as confirmed         |

## 🧩 Flow 7 — Customer Synchronization Across Services (Optional)

```mermaid
sequenceDiagram
    participant CUS as Customer Service
    participant RMQ as RabbitMQ
    participant RES as Reservation Service
    participant DBR as Reservation DB
    CUS ->> RMQ: Publish "CustomerUpdated" event
    RMQ -->> RES: Deliver event
    RES ->> DBR: Update cached customer info
    DBR -->> RES: Save successful
    RES -->> CUS: Acknowledge update
```

### 🧠 Flow Description

| Step | Action                                  | Notes                              |
|------|-----------------------------------------|------------------------------------|
| 1    | Customer data changes                   | e.g., phone number or email        |
| 2    | Event published via RabbitMQ            | Keeps microservices in sync        |
| 3    | Reservation Service updates local cache | Ensures consistency for reporting  |
| 4    | Update acknowledged                     | Optional confirmation or log entry |

## ✅ Summary of All Flows

| Flow                      | Type     | Key Components                          | Communication        |
|---------------------------|----------|-----------------------------------------|----------------------|
| 1. Create Reservation     | Core     | Reservation, Customer, Vehicle, Invoice | REST + RabbitMQ      |
| 2. Cancel Reservation     | Core     | Reservation, Invoice                    | REST + RabbitMQ      |
| 3. List Reservations      | Core     | Reservation                             | REST                 |
| 4. Authentication         | Security | Auth, Gateway                           | REST + JWT           |
| 5. Real-Time Notification | UX       | Reservation, WebSocket                  | WebSocket + RabbitMQ |
| 6. Invoice Event Handling | Async    | Reservation, Invoice                    | RabbitMQ             |
| 7. Customer Sync          | Optional | Customer, Reservation                   | RabbitMQ             |

## 🌐 Global Interaction Overview — IntelliJ-Compatible

```mermaid
flowchart LR
    subgraph CLIENT [API Client]
        A1[Login Request]
        A2[REST API Calls]
        A3[WebSocket Connection]
        A4[Receives Notifications]
    end

    subgraph GATEWAY [API Gateway]
        G1[JWT Validation and Routing]
    end

    subgraph AUTH [Auth Service]
        AU1[Validate Credentials]
        AU2[Issue JWT Token]
    end

    subgraph RES [Reservation Service]
        R1[Create Reservation]
        R2[Cancel Reservation]
        R3[List Reservations]
        R4[Publish Reservation Events]
        R5[Consume Invoice Events]
    end

    subgraph INV [Invoice Service]
        I1[Consume Reservation Events]
        I2[Create Invoice]
        I3[Publish Invoice Events]
    end

    subgraph CUS [Customer Service]
        C1[Manage Customer Data]
        C2[Publish Customer Updates]
    end

    subgraph VEH [Vehicle Service]
        V1[Manage Vehicle Availability]
    end

    subgraph WS [WebSocket Server]
        W1[Handle WS Connections]
        W2[Push Notifications]
    end

    subgraph RMQ [RabbitMQ Message Broker]
        Q1[(Reservation Queue)]
        Q2[(Invoice Queue)]
        Q3[(Customer Queue)]
    end

    subgraph DB [Databases]
        D1[(Auth DB)]
        D2[(Reservation DB)]
        D3[(Invoice DB)]
        D4[(Customer DB)]
        D5[(Vehicle DB)]
    end

%% Authentication Flow
    A1 --> GATEWAY
    GATEWAY --> AUTH
    AUTH --> D1
    AUTH --> GATEWAY
    GATEWAY --> A2
%% Reservation Flow
    A2 --> GATEWAY
    GATEWAY --> RES
    RES --> D2
    RES --> RMQ
    RMQ --> INV
    INV --> D3
    INV --> RMQ
    RMQ --> RES
%% WebSocket Flow
    A3 --> GATEWAY
    GATEWAY --> WS
    RES --> WS
    WS --> A4
%% Customer and Vehicle Sync
    A2 --> GATEWAY
    GATEWAY --> CUS
    CUS --> D4
    CUS --> RMQ
    RMQ --> RES
    RMQ --> VEH
    VEH --> D5
%% Cross-Service Relations
    RES --> INV
    RES --> CUS
    RES --> VEH
    INV --> RES
```

### 🧠 Diagram Explanation

| Area            | Description                                                                  |
|-----------------|------------------------------------------------------------------------------|
| **CLIENT**      | Acts as the entry point. All traffic (REST and WS) goes through the Gateway. |
| **GATEWAY**     | Central access point. Validates JWT, routes to internal services.            |
| **AUTH**        | Issues and refreshes tokens. Uses `Auth DB`.                                 |
| **RESERVATION** | Core domain logic for reservations, publishes and consumes events.           |
| **INVOICE**     | Generates invoices from Reservation events and emits status updates.         |
| **CUSTOMER**    | Manages user data and synchronizes updates across services.                  |
| **VEHICLE**     | Maintains vehicle data and availability.                                     |
| **RMQ**         | Event bus for async communication. Routes messages between services.         |
| **WS SERVER**   | Sends real-time notifications to clients (e.g., reservation status).         |
| **DATABASES**   | Each service owns its schema for true microservice autonomy.                 |

### 🚦 Key Communication Types

| Communication Type       | Examples                                    | Technologies               |
|--------------------------|---------------------------------------------|----------------------------|
| **REST (Sync)**          | `/auth/login`, `/reservations`, `/vehicles` | Spring Web + Feign Clients |
| **Async Messaging**      | Reservation and Invoice events              | RabbitMQ + Spring AMQP     |
| **Real-Time Updates**    | Reservation status notifications            | WebSocket over Gateway     |
| **Schema & Persistence** | Separate schema per service                 | PostgreSQL + Flyway        |
| **Security**             | Authentication, RBAC enforcement            | JWT + API Gateway          |

