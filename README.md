# StudentManagment

Student Management system to manage student real time data

# 🎓 Student Management System API

This is a secure and scalable **Student Management System** REST API built using **Spring Boot 3**, **Spring Security 6**, **JWT authentication**, and **MySQL**. It supports user registration, login, and role-based access control for managing student records.

---

## 📌 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Role-Based Access](#role-based-access)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Authentication Flow](#authentication-flow)
- [Example Users](#example-users)
- [Future Enhancements](#future-enhancements)
- [License](#license)

---

## ✅ Features

- 🔐 **JWT Authentication**
- 👥 **Role-Based Access Control** (`ADMIN`, `USER`)
- 📚 **CRUD** Operations for Students
- 🧾 Uses **DTOs** for clean request/response data
- ❌ Handles **exceptions globally**
- 🧠 **Spring Security 6** (stateless and updated syntax)
- 💡 Modular **service, controller, repository** layers
- ☑️ Built with **Gradle** and **Java 21**

---

## 🛠️ Technology Stack

| Technology      | Version |
| --------------- | ------- |
| Java            | 21      |
| Spring Boot     | 3.2+    |
| Spring Security | 6+      |
| Spring Data JPA | ✅      |
| JWT (jjwt)      | 0.9.1   |
| MySQL           | 8.x     |
| Lombok          | ✅      |
| Gradle          | ✅      |

---

## 🧩 Architecture

- **Controller Layer**: Handles HTTP requests
- **Service Layer**: Business logic
- **Repository Layer**: Database access via JPA
- **Security Layer**: JWT filter, authentication manager, and security config
- **DTOs**: For request and response transformation
- **Exception Handling**: Global handler for runtime errors

---

## 🔐 Role-Based Access

| Endpoint         | Method | Access      | Description                     |
| ---------------- | ------ | ----------- | ------------------------------- |
| `/auth/register` | POST   | Public      | Register as a `USER` or `ADMIN` |
| `/auth/login`    | POST   | Public      | Login to get JWT                |
| `/students`      | POST   | USER, ADMIN | Create student                  |
| `/students/all`  | GET    | ADMIN       | Get all students                |
| `/students/{id}` | GET    | ADMIN       | Get a specific student by ID    |
| `/students/{id}` | PUT    | USER, ADMIN | Update student                  |
| `/students/{id}` | DELETE | USER, ADMIN | Delete student                  |

---

## 📁 Project Structure

src/main/java/com/piyal/studentmanagement
│
├── controller/ # REST Controllers (StudentController, AuthController)
├── dto/ # DTO classes (StudentRequestDTO, AuthRequestDTO, etc.)
├── model/ # Entity classes (User, Student, Role)
├── repository/ # Spring Data JPA interfaces
├── security/ # JWT utilities, filters, and config
├── service/ # Interfaces & Implementations
├── exception/ # Custom exceptions & Global handler
└── StudentManagementApplication.java
