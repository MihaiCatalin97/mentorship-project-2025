# Gateway Service

## Overview

The Gateway is the **single entry point** of the entire application.

Its main responsibilities include:

- Receiving all incoming HTTP requests from clients.
- Authenticating/authorizing requests (in a real production scenario).
- Determining which internal microservice should handle each request based on the URL path.
- Acting as a **reverse proxy**: forwarding requests to the appropriate service and returning the response.
- **Stripping all incoming headers** from client requests before forwarding them, to minimize attack vectors.

In a production environment, **only the gateway exposes a public port**.  
All other services run behind it and are accessible only internally.

---

## Architecture & Ports

Each microservice runs on its own port so they can all be launched simultaneously in IntelliJ or any IDE:

- `gateway-service` → **8080**
- `analytics-service` → 8081
- `auth-service` → 8082
- `billing-service` → 8083
- `customer-service` → 8084
- `notification-service` → 8085
- `reservation-service` → 8086
- `vehicle-service` → 8087

The gateway listens on `http://localhost:8080` and routes requests based on their path.

Routing examples:

- `GET /analytics/...` → analytics-service (8081)
- `POST /auth/login` → auth-service (8082)
- `GET /billing/...` → billing-service (8083)
- `GET /customers/...` → customer-service (8084)
- `POST /notifications/...` → notification-service (8085)
- `GET /reservations/...` → reservation-service (8086)
- `GET /vehicles/...` → vehicle-service (8087)


---

## Configuration (application.yml)

The gateway uses a YAML configuration file to store all service definitions:

```yaml
server:
  port: 8080

services:
  - name: analytics
    host: localhost
    port: 8081
    path: /analytics
  - name: auth
    host: localhost
    port: 8082
    path: /auth
  - name: billing
    host: localhost
    port: 8083
    path: /billing
  - name: customer
    host: localhost
    port: 8084
    path: /customers
  - name: notification
    host: localhost
    port: 8085
    path: /notifications
  - name: reservation
    host: localhost
    port: 8086
    path: /reservations
  - name: vehicle
    host: localhost
    port: 8087
    path: /vehicles
