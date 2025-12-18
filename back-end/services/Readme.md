# Microservices Overview

This folder contains the main microservices of the Car Management platform. Each service is a Spring Boot application and exposes a custom healthcheck endpoint using Spring Boot Actuator.

## Services

- **gateway**  
  API gateway for routing requests to backend services.  
  Exposes `/actuator/healthcheckendpoint` on port 9090.

- **analytics-service**  
  Handles analytics and reporting features.  
  Exposes `/actuator/healthcheckendpoint` on port 9091.

- **auth-service**  
  Manages authentication and authorization.  
  Exposes `/actuator/healthcheckendpoint` on port 9092.

- **billing-service**  
  Responsible for billing and payment processing.  
  Exposes `/actuator/healthcheckendpoint` on port 9093.

- **customer-service**  
  Manages customer data and profiles.  
  Exposes `/actuator/healthcheckendpoint` on port 9094.

- **notification-service**  
  Sends notifications (email, SMS, etc.) to users.  
  Exposes `/actuator/healthcheckendpoint` on port 9095.

- **reservation-service**  
  Handles reservations and booking logic.  
  Exposes `/actuator/healthcheckendpoint` on port 9096.

- **vehicle-service**  
  Manages vehicle data and availability.  
  Exposes `/actuator/healthcheckendpoint` on port 9097.

## Actuator Healthcheck

All services implement a custom healthcheck endpoint using Spring Boot Actuator:

- **Endpoint:** `/actuator/healthcheckendpoint`
- **Response:**
  ```json
  { "status": "UP" }
