# User Service API

## Overview

User Service API is a Spring Boot 3 microservice for managing users, authentication, and profiles. This service provides endpoints for user registration, login, profile management, role assignment, and address management. Future plans include adding OAuth2 and OIDC for enhanced security and authentication.

## Features

- User Registration
- User Login with JWT Authentication
- Profile Management
- Role Assignment
- Address Management
- H2 Database Console
- Swagger API Documentation

## Getting Started

### Prerequisites

- Java 17 or later
- Maven 3.6.0 or later
- Docker (optional, for containerization)

### Installation

1. **Clone the repository**

    ```sh
    git clone https://github.com/vinaykaushik5555/pos-ecommerce.git
    cd pos-ecommerce
    ```

2. **Build the project**

    ```sh
    mvn clean install
    ```

3. **Run the application**

    ```sh
    mvn spring-boot:run
    ```

### Configuration

The application can be configured using `application.yaml`. Here is an example configuration:

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: password
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

jwt:
  secret: your_secret_key_here
  expiration: 3600 # in seconds, e.g., 1 hour

logging:
  level:
    org.springframework: INFO
    com.ps.userservice: DEBUG

springfox:
  documentation:
    swagger:
      enabled: true

security:
  permit-all-paths:
    - /api/v1/users/login
    - /api/v1/users/register
    - /h2-console/**
    - /swagger-ui/**
    - /v3/api-docs/**
```
### API Documentation

Swagger is used for API documentation. Once the application is running, you can access the Swagger UI at:
http://localhost:8080/swagger-ui/

### Testing the API with Postman
```Register a User

Method: POST
URL: http://localhost:8080/api/v1/users/register
Body (JSON):

{
"email": "testuser@example.com",
"password": "password123",
"firstName": "Test",
"lastName": "User"
}
Login User

Method: POST
URL: http://localhost:8080/api/v1/users/login
Body (JSON):

{
"email": "testuser@example.com",
"password": "password123"
}

Get User Profile

Method: GET
URL: http://localhost:8080/api/v1/users/{userId}
Headers:
Authorization: Bearer your_jwt_token_here
Access H2 Console:

Method: GET
URL: http://localhost:8080/h2-console
Access Swagger UI:

Method: GET
URL: http://localhost:8080/swagger-ui/
```
### Project Structure

```src/main/java/com/ps/userservice
├── UserManagementApplication.java
├── config
│   ├── DataInitializer.java
│   ├── SecurityConfig.java
│   └── SwaggerConfig.java
├── controller
│   ├── AddressResource.java
│   └── UserResource.java
├── dto
│   ├── AddressRequest.java
│   ├── AddressResponse.java
│   ├── AssignRolesRequest.java
│   ├── ErrorResponse.java
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   ├── RegisterUserRequest.java
│   ├── UpdateUserProfileRequest.java
│   └── UserResponse.java
├── entity
│   ├── Address.java
│   ├── Role.java
│   └── User.java
├── exception
│   └── GlobalExceptionHandler.java
├── mapper
│   ├── AddressMapper.java
│   └── UserMapper.java
├── repository
│   ├── AddressRepository.java
│   ├── RoleRepository.java
│   └── UserRepository.java
├── service
│   ├── AddressService.java
│   ├── CustomUserDetailsService.java
│   ├── RoleService.java
│   └── UserService.java
└── util
├── JwtAuthenticationEntryPoint.java
├── JwtRequestFilter.java
└── JwtUtil.java
```
### Future Enhancements
OAuth2 and OIDC Integration: The service will be enhanced with OAuth2 and OIDC for better security and authentication.
Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

### License
This project is licensed under the MIT License - see the LICENSE file for details.

### Explanation

- **Overview**: Provides a brief introduction to the User Service API and mentions future enhancements.
- **Features**: Lists the main features of the API.
- **Getting Started**: Includes prerequisites, installation, and configuration instructions.
- **API Documentation**: Details on accessing Swagger for API documentation.
- **H2 Database Console**: Instructions to access the H2 database console.
- **Testing the API with Postman**: Provides examples of how to test the API using Postman.
- **Project Structure**: A detailed project structure for easy navigation.
- **Future Enhancements**: Mentions the plans to add OAuth2 and OIDC.
- **Contributing**: Information on how to contribute to the project.
- **License**: License information for the project.

This `README.md` will help users understand how to set up and use the User Service API, test it with Postman, and contribute to the project while also highlighting future improvements.





