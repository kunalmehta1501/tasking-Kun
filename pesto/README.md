# Task Management API

## Overview

This project is a Task Management API built using Spring Boot. It allows you to create, update, delete, and fetch tasks. The API includes validation for request inputs and detailed error handling.

## Features

- Create, update, delete, and fetch tasks.
- Input validation using JSR-303 annotations.
- Comprehensive test cases for controller methods.

## Prerequisites

- Java 11 or higher
- Maven 3.6.3 or higher
- An IDE like IntelliJ IDEA or Eclipse

## Project Structure

```plaintext
src
├── main
│   ├── java
│   │   └── com
│   │       └── pesto
│   │           ├── Requests
│   │           │   └── TaskRequest.java
│   │           ├── Responses
│   │           │   └── TaskResponse.java
│   │           ├── controller
│   │           │   └── TaskController.java
│   │           ├── entity
│   │           │   └── Task.java
│   │           ├── service
│   │           │   └── TaskService.java
│   │           └── TaskManagementApiApplication.java
│   └── resources
│       └── application.properties
└── test
    └── java
        └── com
            └── pesto
                └── test
                    ├── TaskControllerTest.java
                    └── TaskManagementApiApplicationTests.java

```
## Getting Started
### Clone the repository
```dtd
git clone https://github.com/yourusername/task-management-api.git

```

### Build the project
```dtd
mvn clean install

```

### Run tests
```dtd
mvn test

```

### Run the application
```dtd
mvn spring-boot:run

```
The application will start and be available at http://localhost:8082.

### API Endpoints

- `GET /tasks` - Fetch all tasks
- `GET /tasks/{id}` - Fetch a task by ID
- `POST /tasks` - Create a new task
- `PUT /tasks/{id}` - Update an existing task
- `DELETE /tasks/{id}` - Delete a task by ID
