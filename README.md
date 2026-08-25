# ShopApp Microservices

A microservices-based e-commerce backend built with Java, Spring Boot, and Spring Cloud. This project demonstrates a modular architecture for managing products, orders, and inventory across independent services with centralized routing and service discovery.

## Overview

This application contains the following services:

- Product Service: manages product catalog data and product creation
- Order Service: handles order placement and business flow
- Inventory Service: checks stock availability for requested products
- API Gateway: exposes a single entry point for client requests
- Discovery Server: service registry using Netflix Eureka

The system is designed to show how microservices communicate, register themselves, and work together in a practical online commerce flow.

## Architecture

```text
                              ┌──────────────────────┐
                              │      Client/App      │
                              └──────────┬───────────┘
                                         │
                                         ▼
                              ┌──────────────────────┐
                              │   API Gateway        │
                              │   Spring Cloud       │
                              │   Gateway            │
                              └──────────┬───────────┘
                                         │
                        ┌────────────────┼────────────────┐
                        │                │                │
                        ▼                ▼                ▼
           ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐
           │ Product Service  │  │ Order Service    │  │ Inventory Service│
           │ Spring Boot      │  │ Spring Boot      │  │ Spring Boot      │
           │ MongoDB          │  │ MySQL            │  │ MySQL            │
           └──────────────────┘  └──────────────────┘  └──────────────────┘
                        │                │                │
                        └────────────────┼────────────────┘
                                         ▼
                              ┌──────────────────────┐
                              │ Discovery Server     │
                              │ Eureka               │
                              └──────────────────────┘
```

## Features

- Microservice-based architecture
- Service discovery via Netflix Eureka
- Centralized API routing through Spring Cloud Gateway
- Product storage in MongoDB
- Inventory and order data storage in MySQL
- Inter-service communication between Order and Inventory services
- Docker support for local infrastructure containers
- Scalable foundation for future enhancements such as authentication, monitoring, and CI/CD

## Tech Stack

- Java 21
- Spring Boot 3.x
- Spring Cloud
- Netflix Eureka
- Spring Cloud Gateway
- MongoDB
- MySQL
- Docker
- Maven

## Project Structure

```text
microservices-parent/
├── api-gateway/
│   ├── src/
│   ├── pom.xml
│   └── ...
├── dicovery-server/
│   ├── src/
│   ├── pom.xml
│   └── ...
├── product-service/
│   ├── src/
│   ├── docker-compose.yaml
│   ├── pom.xml
│   └── ...
├── order-service/
│   ├── src/
│   ├── pom.xml
│   └── ...
├── inventory-service/
│   ├── src/
│   ├── docker-compose.yaml
│   ├── pom.xml
│   └── ...
├── pom.xml
├── .gitignore
├── README.md
└── ...
```

## Prerequisites

Before running the project, ensure the following are installed:

- Java 21 or newer
- Maven 3.9+
- Docker Desktop or Docker Engine
- Git

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/your-repository.git
cd your-repository
```

### 2. Start the Required Infrastructure Services

#### MongoDB for Product Service

```bash
cd product-service
docker compose up -d
```

#### MySQL for Inventory Service

```bash
cd inventory-service
docker compose up -d
```

These commands start the local database containers required by the services.

### 3. Start the Discovery Server

```bash
cd dicovery-server
mvn spring-boot:run
```

The Eureka dashboard is available at:

```text
http://localhost:8761
```

### 4. Start the Product Service

```bash
cd product-service
mvn spring-boot:run
```

### 5. Start the Order Service

```bash
cd order-service
mvn spring-boot:run
```

### 6. Start the Inventory Service

```bash
cd inventory-service
mvn spring-boot:run
```

### 7. Start the API Gateway

```bash
cd api-gateway
mvn spring-boot:run
```

Once the gateway is running, it becomes the main public entry point for the backend.

## Service Ports

| Service | Port |
|---------|------|
| Discovery Server | 8761 |
| Product Service | 8082 |
| Order Service | 8081 |
| API Gateway | 8080 |
| Inventory Service | Dynamic / configured via application property |

> Note: Port configuration may vary slightly depending on your environment and runtime setup.

## API Endpoints

### Product Service

Base URL:

```text
http://localhost:8080/api/product
```

#### Create Product

```http
POST /api/product
Content-Type: application/json
```

Example payload:

```json
{
  "name": "Laptop",
  "description": "Gaming laptop",
  "price": 1200.00,
  "skuCode": "LAPTOP-001"
}
```

#### Get All Products

```http
GET /api/product
```

### Order Service

Base URL:

```text
http://localhost:8080/api/order
```

#### Place Order

```http
POST /api/order
Content-Type: application/json
```

Example payload:

```json
{
  "orderItemRequestList": [
    {
      "skuCode": "LAPTOP-001",
      "price": 1200.00,
      "quantity": 1
    }
  ]
}
```

### Inventory Service

Base URL:

```text
http://localhost:8080/api/inventory
```

#### Check Inventory

```http
GET /api/inventory/{skuCode}
```

Example:

```http
GET /api/inventory/LAPTOP-001
```

## Configuration

Each service has its own Spring Boot configuration under:

```text
src/main/resources/application.properties
```

These files configure:

- application names
- database connections
- server ports
- Eureka registration
- API gateway routes

## Build and Run

To build the full project from the parent directory:

```bash
mvn clean install
```

To run a specific service:

```bash
mvn spring-boot:run
```

## Notes

- Product Service uses MongoDB for product catalog storage.
- Order Service calls Inventory Service to validate stock before placing an order.
- Inventory Service stores stock-related data in MySQL.
- Eureka is used for service registration and discovery.
- The API Gateway centralizes external access and routing.

## Contributing

Contributions are welcome. To contribute:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Commit and push to your fork
5. Open a pull request

## License

This project is intended for educational and demonstration purposes.

## Author

Built as a Spring Boot microservices sample project for learning and portfolio demonstration.
