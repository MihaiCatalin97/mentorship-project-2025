# 🧩 Microservices

| Service                  | Responsibilities                                                                        | Communication          |
|--------------------------|-----------------------------------------------------------------------------------------|------------------------|
| **API Gateway**          | Acts as a single entrypoint into the back-end, validates JWT tokens, and enforces RBAC. | REST                   |
| **Auth Service**         | Authenticates users, issues and refreshes JWT tokens, manages roles and permissions.    | REST                   |
| **Customer Service**     | Manage customer data and accounts.                                                      | REST                   |
| **Vehicle Service**      | Manage vehicles, availability, maintenance, and pricing.                                | REST + RMQ             |
| **Reservation Service**  | Core service handling reservations, updates, and cancellations.                         | REST + RMQ + WebSocket |
| **Billing Service**      | Calculates costs, applies discounts, issues invoices.                                   | RMQ                    |
| **Notification Service** | Listens to events and pushes updates via WebSocket.                                     | RMQ + WebSocket        |
| **Analytics Service**    | Consumes events for reporting and analytics.                                            | RMQ                    |
