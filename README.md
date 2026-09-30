# monolithic-e-com

# Monolithic E-Commerce Application

## 1. Project Overview

`monolithic-e-commerce` is a backend e-commerce application built using a **monolithic architecture**.

The application manages users, products, shopping carts, and orders within a single application. Unlike a microservice architecture, all business modules are deployed and run as one application.

The application uses **PostgreSQL** for data persistence and **Docker** for containerization.

### Main Modules

The application consists of the following modules:

- **User Module** – manages users.
- **Product Module** – manages products.
- **Cart Module** – manages shopping carts.
- **Order Module** – manages customer orders.
- **PostgreSQL** – stores application data.
- **Docker** – provides the application and database runtime environment.

---

# 2. Architecture

The application follows a **monolithic architecture**.

```text
                         ┌──────────────────┐
                         │      Client      │
                         │ Postman / Frontend│
                         └────────┬─────────┘
                                  │
                                  ▼
                    ┌──────────────────────────┐
                    │   Monolithic E-Commerce  │
                    │      Spring Boot App     │
                    │                          │
                    │ ┌──────────────────────┐ │
                    │ │    User Module       │ │
                    │ └──────────────────────┘ │
                    │                          │
                    │ ┌──────────────────────┐ │
                    │ │   Product Module     │ │
                    │ └──────────────────────┘ │
                    │                          │
                    │ ┌──────────────────────┐ │
                    │ │    Cart Module       │ │
                    │ └──────────────────────┘ │
                    │                          │
                    │ ┌──────────────────────┐ │
                    │ │    Order Module      │ │
                    │ └──────────────────────┘ │
                    └────────────┬─────────────┘
                                 │
                                 ▼
                       ┌──────────────────┐
                       │    PostgreSQL    │
                       └──────────────────┘
```

All modules are part of the same application and communicate internally through Java service classes rather than HTTP calls between separate services.

---

# 3. Technologies Used

| Technology | Purpose |
|---|---|
| Java | Backend programming language |
| Spring Boot | Application framework |
| Spring Web | Building REST APIs |
| Spring Data JPA | Database access |
| Hibernate | ORM |
| PostgreSQL | Relational database |
| Docker | Containerization |
| Docker Compose | Managing application and database containers |
| Maven | Dependency management and build |
| Postman | API testing |
| Swagger/OpenAPI | API documentation |

---

# 4. Main Application Modules

The application contains four main business modules:

```text
monolithic-e-commerce
│
├── User
│
├── Product
│
├── Cart
│
└── Order
```

Unlike microservices, these modules are not independent applications.

They are compiled, deployed, and run as a **single Spring Boot application**.

---

# 5. User Module

The **User Module** is responsible for managing customer information.

## User Endpoints

| Operation | HTTP Method | Endpoint |
|---|---|---|
| Add User | POST | `/users` |
| Get User | GET | `/users/{id}` |
| Get All Users | GET | `/users` |
| Update User | PUT | `/users/{id}` |

---

## 5.1 Add User

### Endpoint

```http
POST /users
```

### Description

Creates a new user in the e-commerce application.

### Request

```json
{
  "firstName": "John",
  "lastName": "Akanmu",
  "email": "john@example.com",
  "phone": "08012345678"
}
```

### Response

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Akanmu",
  "email": "john@example.com",
  "phone": "08012345678"
}
```

### Status

```text
201 CREATED
```

---

# 5.2 Get User

### Endpoint

```http
GET /users/{id}
```

### Description

Retrieves a user by their ID.

### Example

```http
GET /users/1
```

### Status

```text
200 OK
```

---

# 5.3 Get All Users

### Endpoint

```http
GET /users
```

### Description

Retrieves all users registered in the application.

### Example Response

```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Akanmu",
    "email": "john@example.com"
  },
  {
    "id": 2,
    "firstName": "David",
    "lastName": "Smith",
    "email": "david@example.com"
  }
]
```

### Status

```text
200 OK
```

---

# 5.4 Update User

### Endpoint

```http
PUT /users/{id}
```

### Description

Updates an existing user's information.

### Example

```http
PUT /users/1
```

### Request

```json
{
  "firstName": "John",
  "lastName": "Johnson",
  "email": "john.johnson@example.com",
  "phone": "08098765432"
}
```

### Status

```text
200 OK
```

---

# 6. Product Module

The **Product Module** manages products available in the e-commerce store.

The module supports:

- Creating products
- Retrieving products
- Updating products
- Deleting products
- Searching products

## Product Endpoints

| Operation | HTTP Method | Endpoint |
|---|---|---|
| Create Product | POST | `/products` |
| Get Product | GET | `/products/{id}` |
| Get Products | GET | `/products` |
| Update Product | PUT | `/products/{id}` |
| Delete Product | DELETE | `/products/{id}` |
| Search Products | GET | `/products/search` |

---

# 6.1 Create Product

### Endpoint

```http
POST /products
```

### Description

Creates a new product in the product catalogue.

### Request

```json
{
  "name": "Samsung Galaxy S25",
  "description": "Samsung smartphone",
  "price": 850000,
  "stock": 20
}
```

### Response

```json
{
  "id": 1,
  "name": "Samsung Galaxy S25",
  "description": "Samsung smartphone",
  "price": 850000,
  "stock": 20
}
```

### Status

```text
201 CREATED
```

---

# 6.2 Get Product

### Endpoint

```http
GET /products/{id}
```

### Description

Retrieves a specific product using its ID.

### Example

```http
GET /products/1
```

### Status

```text
200 OK
```

---

# 6.3 Get Products

### Endpoint

```http
GET /products
```

### Description

Retrieves all products.

### Example Response

```json
[
  {
    "id": 1,
    "name": "Samsung Galaxy S25",
    "price": 850000,
    "stock": 20
  },
  {
    "id": 2,
    "name": "Wireless Headphones",
    "price": 50000,
    "stock": 15
  }
]
```

---

# 6.4 Update Product

### Endpoint

```http
PUT /products/{id}
```

### Description

Updates an existing product.

### Example

```http
PUT /products/1
```

### Request

```json
{
  "name": "Samsung Galaxy S25 Ultra",
  "description": "Updated Samsung smartphone",
  "price": 950000,
  "stock": 25
}
```

### Status

```text
200 OK
```

---

# 6.5 Delete Product

### Endpoint

```http
DELETE /products/{id}
```

### Description

Deletes a product from the product catalogue.

### Example

```http
DELETE /products/1
```

### Status

```text
204 NO CONTENT
```

---

# 6.6 Search Products

### Endpoint

```http
GET /products/search
```

### Description

Searches for products using a keyword.

### Example

```http
GET /products/search?keyword=samsung
```

### Response

```json
[
  {
    "id": 1,
    "name": "Samsung Galaxy S25",
    "description": "Samsung smartphone",
    "price": 850000,
    "stock": 20
  }
]
```

---

# 7. Cart Module

The **Cart Module** manages products that customers intend to purchase.

A customer can:

- Add a product to their cart.
- View their cart.
- Remove a product from their cart.
- Proceed to create an order.

## Cart Endpoints

| Operation | HTTP Method | Endpoint |
|---|---|---|
| Add to Cart | POST | `/cart` |
| Remove Cart Item | DELETE | `/cart/{itemId}` |
| Get Cart | GET | `/cart` |

---

# 7.1 Add to Cart

### Endpoint

```http
POST /cart
```

### Description

Adds a product to the customer's shopping cart.

### Request

```json
{
  "productId": 1,
  "quantity": 2
}
```

### Response

```json
{
  "cartId": 1,
  "productId": 1,
  "productName": "Samsung Galaxy S25",
  "quantity": 2,
  "price": 850000,
  "total": 1700000
}
```

### Status

```text
201 CREATED
```

---

# 7.2 Remove Cart Item

### Endpoint

```http
DELETE /cart/{itemId}
```

### Description

Removes an item from the customer's shopping cart.

### Example

```http
DELETE /cart/1
```

### Status

```text
204 NO CONTENT
```

---

# 7.3 Get Cart

### Endpoint

```http
GET /cart
```

### Description

Retrieves the customer's current shopping cart.

### Response

```json
{
  "cartId": 1,
  "items": [
    {
      "productId": 1,
      "productName": "Samsung Galaxy S25",
      "quantity": 2,
      "price": 850000,
      "total": 1700000
    },
    {
      "productId": 2,
      "productName": "Wireless Headphones",
      "quantity": 1,
      "price": 50000,
      "total": 50000
    }
  ],
  "totalAmount": 1750000
}
```

### Status

```text
200 OK
```

---

# 8. Order Module

The **Order Module** is responsible for converting a customer's shopping cart into an order.

## Order Endpoint

| Operation | HTTP Method | Endpoint |
|---|---|---|
| Create Order | POST | `/orders` |

---

# 8.1 Create Order

### Endpoint

```http
POST /orders
```

### Description

Creates an order using the products currently in the customer's cart.

The application performs the following operations:

```text
Customer
   │
   ▼
Shopping Cart
   │
   ▼
Validate Cart
   │
   ▼
Check Product Stock
   │
   ▼
Calculate Total
   │
   ▼
Create Order
   │
   ▼
Create Order Items
   │
   ▼
Clear Cart
```

### Request

```json
{
  "userId": 1
}
```

### Response

```json
{
  "orderId": 1001,
  "userId": 1,
  "status": "PENDING",
  "totalAmount": 1750000,
  "items": [
    {
      "productId": 1,
      "productName": "Samsung Galaxy S25",
      "quantity": 2,
      "price": 850000
    },
    {
      "productId": 2,
      "productName": "Wireless Headphones",
      "quantity": 1,
      "price": 50000
    }
  ]
}
```

### Status

```text
201 CREATED
```

---

# 9. Database

The application uses **PostgreSQL** as its database.

Because this is a monolithic application, the different modules can share the same PostgreSQL database.

A possible database structure is:

```text
ecommerce_db
│
├── users
│
├── products
│
├── carts
│
├── cart_items
│
├── orders
│
└── order_items
```

---

# 10. Database Relationships

The major relationships can be represented as follows:

```text
                    ┌─────────────┐
                    │    User     │
                    └──────┬──────┘
                           │
                    1      │      *
                           │
                    ┌──────▼──────┐
                    │    Cart     │
                    └──────┬──────┘
                           │
                    1      │      *
                           │
                    ┌──────▼──────┐
                    │ Cart Item   │
                    └──────┬──────┘
                           │
                           │ *
                           │
                           │ 1
                    ┌──────▼──────┐
                    │   Product   │
                    └─────────────┘


                    ┌─────────────┐
                    │    User     │
                    └──────┬──────┘
                           │
                    1      │      *
                           │
                    ┌──────▼──────┐
                    │    Order    │
                    └──────┬──────┘
                           │
                    1      │      *
                           │
                    ┌──────▼──────┐
                    │ Order Item  │
                    └──────┬──────┘
                           │
                           │ *
                           │
                           │ 1
                    ┌──────▼──────┐
                    │   Product   │
                    └─────────────┘
```

---

# 11. Project Structure

A recommended Spring Boot project structure is:

```text
monolithic-e-commerce/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/ecommerce/
│   │   │       │
│   │   │       ├── user/
│   │   │       │   ├── controller/
│   │   │       │   ├── service/
│   │   │       │   ├── repository/
│   │   │       │   ├── entity/
│   │   │       │   └── dto/
│   │   │       │
│   │   │       ├── product/
│   │   │       │   ├── controller/
│   │   │       │   ├── service/
│   │   │       │   ├── repository/
│   │   │       │   ├── entity/
│   │   │       │   └── dto/
│   │   │       │
│   │   │       ├── cart/
│   │   │       │   ├── controller/
│   │   │       │   ├── service/
│   │   │       │   ├── repository/
│   │   │       │   ├── entity/
│   │   │       │   └── dto/
│   │   │       │
│   │   │       ├── order/
│   │   │       │   ├── controller/
│   │   │       │   ├── service/
│   │   │       │   ├── repository/
│   │   │       │   ├── entity/
│   │   │       │   └── dto/
│   │   │       │
│   │   │       ├── exception/
│   │   │       └── config/
│   │   │
│   │   └── resources/
│   │       ├── application.yml
│   │       └── application-dev.yml
│   │
│   └── test/
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

# 12. Layered Architecture

Each module follows a layered architecture:

```text
             Client
                │
                ▼
          ┌─────────────┐
          │ Controller  │
          └──────┬──────┘
                 │
                 ▼
          ┌─────────────┐
          │   Service   │
          └──────┬──────┘
                 │
                 ▼
         ┌──────────────┐
         │  Repository  │
         └──────┬───────┘
                │
                ▼
          ┌─────────────┐
          │ PostgreSQL  │
          └─────────────┘
```

### Controller

Handles HTTP requests and responses.

### Service

Contains the application's business logic.

### Repository

Communicates with PostgreSQL through Spring Data JPA.

### Entity

Represents database tables.

### DTO

Controls the data exchanged through the REST API.

---

# 13. Docker

Docker is used to run the Spring Boot application and PostgreSQL database in containers.

The project can contain:

```text
monolithic-e-commerce/
│
├── Dockerfile
├── docker-compose.yml
└── src/
```

Unlike the previous microservice architecture, there is only **one backend application container**.

```text
┌─────────────────────────────────┐
│        Docker Environment       │
│                                 │
│  ┌───────────────────────────┐  │
│  │ Monolithic Spring Boot    │  │
│  │ Application               │  │
│  │                           │  │
│  │ User                      │  │
│  │ Product                   │  │
│  │ Cart                      │  │
│  │ Order                     │  │
│  └─────────────┬─────────────┘  │
│                │                │
│                ▼                │
│  ┌───────────────────────────┐  │
│  │ PostgreSQL                │  │
│  └───────────────────────────┘  │
│                                 │
└─────────────────────────────────┘
```

---

# 14. Docker Compose

Example `docker-compose.yml`:

```yaml
services:

  postgres:
    image: postgres:16
    container_name: ecommerce-postgres
    environment:
      POSTGRES_DB: ecommerce_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  ecommerce-app:
    build: .
    container_name: monolithic-ecommerce
    ports:
      - "8080:8080"
    depends_on:
      - postgres
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/ecommerce_db
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: postgres

volumes:
  postgres_data:
```

---

# 15. Application Configuration

Example `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ecommerce_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true

server:
  port: 8080
```

When the application runs **inside Docker**, the PostgreSQL hostname should normally be the Docker Compose service name:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://postgres:5432/ecommerce_db
```

When running Spring Boot directly from IntelliJ while PostgreSQL is exposed to the host:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ecommerce_db
```

---

# 16. Running the Application

## Using Docker

Start Docker Desktop and run:

```bash
docker compose up --build
```

Or run in detached mode:

```bash
docker compose up -d --build
```

Check the containers:

```bash
docker ps
```

Stop the application:

```bash
docker compose down
```

---

# 17. Running Without Docker

If PostgreSQL is installed and running locally:

```bash
mvn spring-boot:run
```

Or build the application:

```bash
mvn clean package
```

Then run:

```bash
java -jar target/monolithic-e-commerce.jar
```

---

# 18. API Testing

The REST APIs can be tested using:

- Postman
- Swagger UI
- IntelliJ HTTP Client
- Frontend application

The application can expose Swagger UI at a URL similar to:

```text
http://localhost:8080/swagger-ui/index.html
```

The exact URL depends on the Springdoc/OpenAPI configuration.

---

# 19. Recommended Testing Flow

A typical e-commerce workflow is:

### Step 1 — Create User

```http
POST /users
```

### Step 2 — Create Product

```http
POST /products
```

### Step 3 — Add Product to Cart

```http
POST /cart
```

### Step 4 — View Cart

```http
GET /cart
```

### Step 5 — Create Order

```http
POST /orders
```

The overall flow is:

```text
Create User
     │
     ▼
Create Product
     │
     ▼
Add Product to Cart
     │
     ▼
View Cart
     │
     ▼
Create Order
     │
     ▼
Order Created
```

---

# 20. Complete API Summary

## User

| Operation | Method | Endpoint |
|---|---|---|
| Add User | POST | `/users` |
| Get User | GET | `/users/{id}` |
| Get All Users | GET | `/users` |
| Update User | PUT | `/users/{id}` |

## Product

| Operation | Method | Endpoint |
|---|---|---|
| Create Product | POST | `/products` |
| Get Product | GET | `/products/{id}` |
| Get Products | GET | `/products` |
| Update Product | PUT | `/products/{id}` |
| Delete Product | DELETE | `/products/{id}` |
| Search Products | GET | `/products/search` |

## Cart

| Operation | Method | Endpoint |
|---|---|---|
| Add to Cart | POST | `/cart` |
| Remove Cart Item | DELETE | `/cart/{itemId}` |
| Get Cart | GET | `/cart` |

## Order

| Operation | Method | Endpoint |
|---|---|---|
| Create Order | POST | `/orders` |

---

# 21. Error Handling

The application should use standard HTTP status codes.

| Status | Meaning |
|---|---|
| 200 OK | Request successful |
| 201 CREATED | Resource successfully created |
| 204 NO CONTENT | Request successful without response body |
| 400 BAD REQUEST | Invalid request |
| 404 NOT FOUND | Resource does not exist |
| 409 CONFLICT | Resource conflict |
| 500 INTERNAL SERVER ERROR | Unexpected server error |

Example:

```json
{
  "status": 404,
  "message": "Product not found",
  "timestamp": "2026-09-30T12:00:00"
}
```

---

# 22. Monolithic vs Microservice Architecture

The original project was described as a microservice application. The current project is a monolithic application.

### Microservice Architecture

```text
User Service       → Application
Product Service    → Application
Order Service      → Application
```

Each service is independently deployed.

### Monolithic Architecture

```text
                ┌───────────────────────────┐
                │     Single Application     │
                │                           │
                │ User                      │
                │ Product                   │
                │ Cart                      │
                │ Order                     │
                └─────────────┬─────────────┘
                              │
                              ▼
                         PostgreSQL
```

The monolithic approach keeps all modules in one deployable application while still allowing the codebase to be organized into separate business modules.

---

# 23. Conclusion

`monolithic-e-commerce` is a Spring Boot-based e-commerce backend that provides functionality for:

- User management
- Product management
- Product searching
- Shopping cart management
- Order creation

The application uses **Spring Boot, Spring Data JPA, PostgreSQL, Docker, and REST APIs**.

All business modules run inside one Spring Boot application and share the PostgreSQL database.

The application follows a layered architecture:

```text
Controller
     ↓
Service
     ↓
Repository
     ↓
PostgreSQL
```

This structure keeps the application organized while allowing the User, Product, Cart, and Order modules to work together as one deployable e-commerce system.