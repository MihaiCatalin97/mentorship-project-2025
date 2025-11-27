# customer-service

Small Java/Maven microservice responsible for customer-related functionality 

## Structure
- `com.project.mentorship.service.customer` - main source code
- `pom.xml` - maven build configuration
- `README.md` - project documentation
  [CustomerServiceApplication](src/main/java/com/project/mentorship/service/customer/CustomerServiceApplication.java)
## Build
```bash
mvn install -DskipTests
```
## API
### POST @ /customers
#### Description
Create a new customer (temporarily stored in memory). The flow follows the n-layer architecture:
Controller → Mapper → Service → Repository.
#### Validations
No validations
#### Request examples
```
{
  "id": "11111111-1111-1111-1111-111111111111",
  "userId": "33333333-3333-3333-3333-333333333333",
  "firstName": "Olivia",
  "lastName": "Taylor",
  "email": "olivia.taylor@example.com",
  "phone": "+40700111222",
  "createdAt": "2025-02-10T14:30:00Z",
  "updatedAt": "2025-09-20T08:45:00Z"
}
#### Request examples
```
{
"id": "e2c82339-7057-4835-bd1a-6449ee99e2e3",
"userId": "33333333-3333-3333-3333-333333333333",
"firstName": "Olivia",
"lastName": "Taylor",
"email": "olivia.taylor@example.com",
"phone": "+40700111222",
"createdAt": "2025-11-24T10:15:30.123456Z",
"updatedAt": null
}
```
