# E-Clearance Certificate System

A web-based **E-Clearance Certificate System** developed to digitize and simplify the college clearance process for students. The system aims to reduce paperwork, save time, and make the clearance process more organized and transparent.

## About the Project

The E-Clearance Certificate System is designed to help students complete their college clearance process digitally instead of visiting different departments for manual signatures.

Students can log in to the system and access their dashboard. The system is being developed to support clearance verification by different college departments, such as the library, hostel, and accounts department.

## Objectives

* Digitize the traditional college clearance process.
* Reduce paperwork and manual effort.
* Make the clearance process faster and more organized.
* Provide a centralized platform for students and college staff.
* Maintain student and clearance-related information.

## Technology Stack

| Technology      | Purpose                           |
| --------------- | --------------------------------- |
| Java 21         | Backend programming               |
| Spring Boot     | Backend framework                 |
| Spring MVC      | Web request handling              |
| Spring Data JPA | Database operations               |
| Hibernate       | ORM                               |
| MySQL 8         | Database                          |
| Thymeleaf       | Dynamic HTML pages                |
| HTML            | Web page structure                |
| CSS             | Web page styling                  |
| Maven           | Project and dependency management |
| REST API        | Backend communication             |

## Current Features

* Student and user management.
* User login using email and password.
* Role-based redirection for Student, Staff, and Admin.
* Student profile management.
* Dynamic student dashboard displaying student information.
* REST APIs for student records.
* MySQL database integration.

## Project Structure

```text
digital-college-clearance/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── in/ac/mitmeerut/clearance/
│       │       ├── controller/
│       │       ├── entity/
│       │       ├── enums/
│       │       ├── repository/
│       │       ├── service/
│       │       └── DigitalCollegeClearanceApplication.java
│       │
│       └── resources/
│           ├── templates/
│           ├── static/
│           └── application.properties
│
├── pom.xml
└── README.md
```

## Database Configuration

The project uses MySQL.

Create a database:

```sql
CREATE DATABASE college_clearance;
```

Configure your database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/college_clearance
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Replace the username and password with your local MySQL credentials. Do not upload actual passwords to GitHub.

## How to Run the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/4yushjha/digital-college-clearance.git
   ```

2. Open the project in Eclipse or IntelliJ IDEA.

3. Configure your MySQL database in `application.properties`.

4. Make sure Java 21 and Maven are installed.

5. Run the main application class:

   `DigitalCollegeClearanceApplication.java`

6. Open the application in your browser:

   ```text
   http://localhost:8080/login
   ```

## Main API Endpoints

| HTTP Method | Endpoint         | Description                |
| ----------- | ---------------- | -------------------------- |
| POST        | `/students`      | Create a student           |
| GET         | `/students`      | Get all students           |
| GET         | `/students/{id}` | Get a student by ID        |
| PUT         | `/students/{id}` | Update student information |
| DELETE      | `/students/{id}` | Delete a student           |

## Future Enhancements

* Department-wise clearance verification.
* Staff dashboard for handling clearance requests.
* Admin dashboard for monitoring clearance activities.
* Clearance status tracking.
* Automatic clearance certificate generation.
* Improved authentication and authorization.

## Developer

**Ayush Jha**

B.Tech – Computer Science and Engineering
Meerut Institute of Technology (MIT Meerut)

GitHub: [@4yushjha](https://github.com/4yushjha)

---

⭐ If you find this project interesting, consider giving the repository a star!
