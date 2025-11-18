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

{
"id": "550e8400-e29b-41d4-a716-446655440000",
"customerId": "123e4567-e89b-12d3-a456-426614174000",
"vehicleId": "223e4567-e89b-12d3-a456-426614174000",
"startTime": "2025-11-14T10:00:00+01:00",
"endTime": "2025-11-14T12:00:00+01:00",
"status": "CONFIRMED",
"createdAt": "2025-11-14T09:50:00+01:00",
"updatedAt": "2025-11-14T09:50:00+01:00"
}


#### Response examples
{
"id": "550e8400-e29b-41d4-a716-446655440000",
"customerId": "123e4567-e89b-12d3-a456-426614174000",
"vehicleId": "223e4567-e89b-12d3-a456-426614174000",
"startTime": "2025-11-14T10:00+01:00",
"endTime": "2025-11-14T12:00+01:00",
"status": "CONFIRMED",
"createdAt": "2025-11-14T09:50+01:00",
"updatedAt": "2025-11-14T09:50+01:00"
}

