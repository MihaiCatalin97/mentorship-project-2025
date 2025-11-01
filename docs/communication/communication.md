# Communication between services

## 🔌 REST Endpoints Overview

### Customer Service

```
GET /customers
GET /customers/{id}
POST /customers
PUT /customers/{id}
DELETE /customers/{id}
```

### Vehicle Service

```
GET /vehicles
GET /vehicles/{id}
POST /vehicles
PATCH /vehicles/{id}/status
```

### Reservation Service

```
GET /reservations
GET /reservations/{id}
POST /reservations
PATCH /reservations/{id}/status
```

### Billing Service

```
GET /invoices
GET /invoices/{id}
```
---

## 💬 Example RabbitMQ Events

### reservation.created

```
{
  "reservationId": "b1f9b0a1-ffab-45a9-bb19-4eabcf238fe1",
  "customerId": "a7f1b920-2b09-456b-b5c2-f2e7c2b01a1d",
  "vehicleId": "c6e331df-fb3d-4d3e-b056-24a71a501e01",
  "startDate": "2025-10-28",
  "endDate": "2025-10-30"
}
```

### invoice.created

```
{
  "invoiceId": "inv-001",
  "reservationId": "b1f9b0a1-ffab-45a9-bb19-4eabcf238fe1",
  "amount": 150.0
}
```

### reservation.completed

```
{
  "reservationId": "b1f9b0a1-ffab-45a9-bb19-4eabcf238fe1",
  "status": "COMPLETED"
}
```
---

## 🔔 WebSocket Channels

| Channel                            | Purpose                              |
|------------------------------------|--------------------------------------|
| `/topic/reservations/{customerId}` | Reservation status updates           |
| `/topic/invoices/{customerId}`     | Invoice creation and payment updates |
| `/topic/system/alerts`             | System-wide notifications            |

Example message:

```
{
  "type": "RESERVATION_CONFIRMED",
  "reservationId": "b1f9b0a1-ffab-45a9-bb19-4eabcf238fe1",
  "message": "Your reservation has been confirmed."
}
```
