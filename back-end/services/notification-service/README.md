## Build
```bash
mvn install -DskipTests
```
## API
## POST @ /notifications
## Description
This endpoint allows the creation of a new notification.
## Validations
No validations (happy flow only).

## Request examples
```
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "reservationId": "123e4567-e89b-12d3-a456-426614174000",
  "customerId": "223e4567-e89b-12d3-a456-426614174000",
  "type": "EMAIL",
  "status": "PENDING",
  "sentAt": "2025-11-24T10:00:00Z",
  "createdAt": "2025-11-24T10:20:00Z"
}
```

## Response examples
```
{
  "id": "9021d196-a545-4025-8e40-6c32305658a9",
  "reservationId": "123e4567-e89b-12d3-a456-426614174000",
  "customerId": "223e4567-e89b-12d3-a456-426614174000",
  "type": "EMAIL",
  "status": "PENDING",
  "sentAt": null,
  "createdAt": "2025-11-28T11:19:05Z"
}
```