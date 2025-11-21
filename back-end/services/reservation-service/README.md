# reservation-service

## Overview
Maven module for vehicle reservation operations.(Initial setup)

## Structure
- [ReservationServiceApplication](src/main/java/com/project/mentorship/service/reservation/ReservationServiceApplication.java) — entry point dummy (psvm)

## Build
```bash
mvn install -DskipTests
```
## API
### POST @ /reservations
#### Description
Create a new reservation (temporarily stored in memory). The flow follows the n-layer architecture: 
Controller → Mapper → Service → Repository.
#### Validations
No validations
#### Request examples
```
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "customerId": "123e4567-e89b-12d3-a456-426614174000",
  "vehicleId": "223e4567-e89b-12d3-a456-426614174000",
  "startTime": "2025-11-20T10:00:00Z",
  "endTime": "2025-11-20T12:00:00Z",
  "status": "PENDING",
  "createdAt": "2025-11-20T09:55:00Z",
  "updatedAt": "2025-11-20T09:55:00Z"
}

```

#### Response examples
```
{
    "id": "e2c82339-7057-4835-bd1a-6449ee99e2e3",
    "customerId": "123e4567-e89b-12d3-a456-426614174000",
    "vehicleId": "223e4567-e89b-12d3-a456-426614174000",
    "startTime": "2025-11-20T10:00Z",
    "endTime": "2025-11-20T12:00Z",
    "status": "PENDING",
    "createdAt": "2025-11-21T09:11:26.940694800Z",
    "updatedAt": null
}
```

