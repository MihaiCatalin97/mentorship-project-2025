# analytics-service  
## Overview  
Maven module for analytics/statistics operations. (Initial setup)

## Structure  
AnalyticsServiceApplication — entry point (psvm)

## Build  
```bash
mvn install -DskipTests
```

## API  
### POST @ /analytics/statistics

#### Description  
Create a new statistics entry (temporarily stored in memory).  
The flow follows the n-layer architecture: Controller → Mapper → Service → Repository.  
On success, the endpoint returns HTTP 201 and echoes the created statistics as received from the request.

#### Validations  
No validations

#### Request examples  
```
{
"date": "2025-01-15T00:00:00Z",
"totalReservations": 42,
"totalRevenue": 950.75
}
```

### Response examples  
```
{
"id": "b2979c1d-25b4-476b-a3b3-db4ad0b9bb48",
"date": "2025-01-15T00:00:00Z",
"totalReservations": 42,
"totalRevenue": 950.75,
"createdAt": "2025-12-04T13:06:06.5438286Z"
}
```

