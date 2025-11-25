# vehicle-service

## Overview
Maven module for vehicle reservation operations.(Initial setup)

## Structure
- [BillingServiceApplication](src/main/java/com/project/mentorship/service/billing/BillingServiceApplication.java) — entry point dummy (psvm)

## Build
```bash
mvn install -DskipTests
```
## API
### POST @ /invoice-line
#### Description
Create a new invoice (temporarily stored in memory). The flow follows the n-layer architecture:
Controller → Mapper → Service → Repository.
#### Validations
No validations
#### Request examples
```
{
  "invoiceId": "11111111-1111-1111-1111-111111111111",
  "description": "Updated description",
  "quantity": 12,
  "unitPrice": 100.50,
  "total": 1200.70
}

```

#### Response examples
```
{
    "id": "876c8970-f1ff-4171-bb36-05776c64b960",
    "invoiceId": "11111111-1111-1111-1111-111111111111",
    "description": "Updated description",
    "quantity": 12,
    "unitPrice": 100.5,
    "total": 1200.7
}
```

