# Task Management System API

A Spring Boot REST API for task management with JWT Authentication, Role-Based Authorization, MySQL, Swagger Documentation, and Docker support.

## Features

* User Registration
* User Login with JWT Authentication
* Role-Based Access Control (ADMIN, USER)
* Create Task
* Get All Tasks
* Get Task By ID
* Get Tasks By User ID
* Update Task
* Delete Task
* Swagger API Documentation
* Docker Containerization
* MySQL Database Integration

## Tech Stack

* Java 17
* Spring Boot
* Spring Security
* JWT
* Spring Data JPA
* MySQL
* Swagger OpenAPI
* Docker
* Maven

## API Endpoints

### Authentication

POST /api/auth/register

POST /api/auth/login

### Tasks

GET /api/tasks

GET /api/tasks/{id}

GET /api/tasks/user/{userId}

POST /api/tasks

PUT /api/tasks/{id}

DELETE /api/tasks/{id}

## Swagger UI

http://localhost:8080/swagger-ui/index.html

## Docker

Build:

docker build -t taskmanagementapi .

Run:

docker run -p 8080:8080 taskmanagementapi

## Author

Shafrin M
