# Spring Boot Project

## Overview
This project is a modern, robust application built using Spring Boot 3, demonstrating best practices in enterprise Java development. 
It leverages Spring Data JPA with Hibernate for efficient and effective data handling using Java 21.

## Key Features
- **Spring Boot 3**: Utilizes the latest Spring Boot framework for rapid development and ease of application setup.
- **Spring Data JPA & Hibernate**: Incorporates Spring Data JPA for repository handling, coupled with Hibernate for object-relational mapping, offering powerful and flexible data access capabilities.
- **Java 21**: Developed with Java 21, ensuring the application benefits from the latest language improvements and features.
- **Testing**: Features comprehensive integration and unit tests, ensuring high reliability and maintainability.
- **Domain-Driven Design**: Adheres to Domain-Driven Design principles, leading to a clean, modular, and scalable architecture.
- **Test-Driven Development (TDD)**: Embraces the TDD methodology for development.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
- JDK 21
- PostgreSQL server optionally loaded with [DVD Rental Data](https://www.postgresqltutorial.com/postgresql-getting-started/postgresql-sample-database/).

### Installing
1. **Clone the repository:**
   ```bash
   git clone git@github.com:CoryKniefel/spring-data-dvd-rental.git
   ```

2. **Navigate to the project directory:**
   ```bash
   cd spring-data-dvd-rental
   ```

3. **Build the project:**
      ```bash
      gradle build
      ```

4. **Run the application:**
   ```bash
   java -Dspring.profiles.active=dev -jar build/libs/spring-data-dvd-rental-0.0.1-SNAPSHOT.jar
   ```

## Running the Tests
This project places a strong emphasis on testing. To run the tests:
  ```bash
  gradle test
  ```

- **Integration Tests:**
  Integration tests can be run separately using a dedicated Gradle command.
  ```bash
  gradle integrationtestClasses
  ```

## Built With
- [Spring Boot](https://spring.io/projects/spring-boot) - The framework used
- [Gradle](https://gradle.org/) - Dependency Management
- [Hibernate](https://hibernate.org/orm/) - ORM Tool
- [JUnit](https://junit.org/) - Testing Framework


## Authors
- [Cory Kniefel](https://github.com/CoryKniefel/)

