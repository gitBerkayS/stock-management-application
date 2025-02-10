# Management System for Stock Trading Company

**Course:** INFO3150  
**Project Title:** Stock Trading Management System    
**Team Members:**  
- Berkay Sefayi  
- Inderpreet Kaur  
- Ryan Mendoza  
- Landon Taylor  
- Bryan Andersen 

---

## Introduction

This project is developed as part of the INFO3150 course. It is aimed at helping each team-member understand the computer software design process and activities. The current iteration is a prototype login page.

---

## System Architecture

- **Backend:** Java Spring Boot application.
  - **Security:** Spring Security for authentication and authorization.
  - **Database:** H2 in-memory database for prototyping.
  - **Model:** JPA entities representing users and roles.
  - **Controller:** REST and MVC controllers for handling login, signup, and redirections.
  - **Service**: Spring Security authentication methods

---

## Dependencies

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Thymeleaf**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Maven**

---

## Usage

- **Signup:**  
  Navigate to `/signup` to create a new user. Default user role is trader.
  Admin users are required to be hard-coded into the database.
  
- **Login:**  
  Access the `/login` page to authenticate the user.
  This page automatically pops up as deafult page for security.
  On login, users are redirected to the dashboard according to their role:
  - **Admin:** `/admin_dashboard`
  - **Trader:** `/trader_dashboard`
  
- **Logout:**  
  Any logout link will redirect to `/logout`, and point back to the `/login` screen.
