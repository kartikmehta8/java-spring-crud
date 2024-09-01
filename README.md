
# Spring Boot CRUD REST API

This is a simple Spring Boot application that demonstrates a basic CRUD (Create, Read, Update, Delete) REST API using an in-memory H2 database.

## Table of Contents

- [Introduction](#introduction)
- [Requirements](#requirements)
- [Project Structure](#project-structure)
- [Endpoints](#endpoints)
- [Detailed Code Explanation](#detailed-code-explanation)
  - [ApiApplication.java](#apiapplicationjava)
  - [User.java](#userjava)
  - [UserRepository.java](#userrepositoryjava)
  - [UserController.java](#usercontrollerjava)
  - [application.properties](#applicationproperties)
- [How to Run](#how-to-run)
- [H2 Database Console](#h2-database-console)

## Introduction

This project is a basic example of a Spring Boot application that provides CRUD operations for managing users. It uses Spring Data JPA to interact with an H2 in-memory database.

## Project Structure

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── rest
│   │           └── api
│   │               ├── ApiApplication.java
│   │               ├── controller
│   │               │   └── UserController.java
│   │               ├── model
│   │               │   └── User.java
│   │               └── repository
│   │                   └── UserRepository.java
│   └── resources
│       └── application.properties
└── test
    └── java
        └── com
            └── rest
                └── api
                    └── ApiApplicationTests.java
```

## Endpoints

### 1. Create a New User
- **Endpoint:** `POST /api/users`
- **Description:** Creates a new user in the database.
- **Sample JSON Body:**
    ```json
    {
      "name": "John Doe",
      "email": "john.doe@example.com"
    }
    ```

### 2. Get All Users
- **Endpoint:** `GET /api/users`
- **Description:** Retrieves a list of all users in the database.
- **Sample Response:**
    ```json
    [
      {
        "id": 1,
        "name": "John Doe",
        "email": "john.doe@example.com"
      },
      {
        "id": 2,
        "name": "Jane Smith",
        "email": "jane.smith@example.com"
      }
    ]
    ```

### 3. Get a Single User by ID
- **Endpoint:** `GET /api/users/{id}`
- **Description:** Retrieves the details of a user by their ID.
- **Sample URL:** `/api/users/1`

### 4. Update an Existing User
- **Endpoint:** `PUT /api/users/{id}`
- **Description:** Updates the details of an existing user by their ID.
- **Sample URL:** `/api/users/1`
- **Sample JSON Body:**
    ```json
    {
      "name": "Johnathan Doe",
      "email": "johnathan.doe@example.com"
    }
    ```

### 5. Delete a User
- **Endpoint:** `DELETE /api/users/{id}`
- **Description:** Deletes a user from the database by their ID.
- **Sample URL:** `/api/users/1`

## Detailed Code Explanation

### ApiApplication.java

This is the main class that bootstraps your Spring Boot application.

- **Package Declaration and Imports:** The `package` declaration and `import` statements at the top of the file ensure that the classes are properly organized and that required external classes are available.
- **Main Class:** The `ApiApplication` class is the entry point for your application. The `@SpringBootApplication` annotation indicates that this is the main configuration class.
- **Main Method:** The `main` method uses `SpringApplication.run` to launch the application.

### User.java

This file defines the `User` entity class, which represents the structure of the user data stored in the database.

- **Annotations:** The `@Entity` and `@Table(name = "users")` annotations tell JPA to map this class to a database table named `users`.
- **Fields:** The `id`, `name`, and `email` fields represent the columns in the `users` table. The `@Id` and `@GeneratedValue` annotations specify that `id` is the primary key and should be generated automatically.
- **Constructors and Methods:** The class includes a no-argument constructor, a parameterized constructor, and getter and setter methods for each field.

### UserRepository.java

This interface extends `JpaRepository` to provide CRUD operations for the `User` entity.

- **JpaRepository:** Extending `JpaRepository<User, Long>` provides built-in methods for common data access operations.

### UserController.java

This class handles HTTP requests and maps them to specific actions.

- **RestController and RequestMapping:** The `@RestController` annotation marks this class as a controller, and `@RequestMapping("/api/users")` specifies the base URL for all methods in this class.
- **Dependency Injection:** The `UserRepository` is injected using `@Autowired`.
- **CRUD Methods:** The class includes methods for creating, reading, updating, and deleting users.

### application.properties

This file contains configuration settings for your Spring Boot application.

- **Database Configuration:** The properties set up an in-memory H2 database with the URL `jdbc:h2:mem:testdb`.
- **JPA Configuration:** The properties include settings for the Hibernate dialect, showing SQL statements, and automatically updating the database schema.

## How to Run

1. Clone the repository.
2. Ensure that you have Java 17 or higher installed.
3. Navigate to the project directory.
4. Run the application using your IDE or the command line (`mvn spring-boot:run` or `./gradlew bootRun`).
5. Access the endpoints using a tool like Postman or `curl`.

## H2 Database Console

The H2 database console is available at `http://localhost:8080/h2-console`. Use the following settings to connect:

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** `password`
