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

# Task Management Frontend

This project is a frontend application for a task management system built using React.

## Getting Started

### Clone the Repository

```sh
git clone https://github.com/yourusername/task-management-frontend.git
cd task-management-frontend
```

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

## Project Structure

```plaintext
src/
├── components/
│   ├── TaskForm.js         # Component for creating or editing tasks
│   ├── TaskList.js         # Component for displaying a list of tasks
│   ├── TaskFilter.js       # Component for filtering tasks
├── pages/
│   ├── TaskFormPage.js     # Page for creating or editing tasks
│   ├── TaskListPage.js     # Page for displaying the task list
├── App.js                  # Main application component
├── index.js                # Entry point of the application
├── App.css                 # Global styles for the application

```

### API Integration
The frontend communicates with the task management API hosted at http://localhost:8082. Ensure the API is running locally or update the API URL accordingly.

### Features

- **TaskForm**: Create or edit tasks with fields for title, description, and status (TODO, IN_PROGRESS, DONE).
- **TaskList**: Display a list of tasks with options to delete or edit each task.
- **TaskFilter**: Filter tasks by status (All, TODO, IN_PROGRESS, DONE).
- **Responsive Design**: Application is designed to be responsive and works well on both desktop and mobile devices.
