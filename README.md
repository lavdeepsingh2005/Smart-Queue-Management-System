# Smart Queue Management System

This repository contains the prototype of Smart Queue Management System. The prototype focuses on customer registration, service selection, token generation, priority-based queue ordering, token tracking, and an initial waiting-time estimate.

## Current scope

- Customer registration with basic server-side validation.
- Selection from a predefined list of services.
- Token generation using the priority code, service code, and database-generated ID.
- Three queue priorities: Emergency/VIP, Assisted, and Regular.
- Priority ordering, with FIFO order within each priority.
- Token lookup and queue-position display.
- A public queue page that refreshes periodically.
- Estimated waiting time based on configured average service durations.
- MySQL persistence through Spring Data JPA and Hibernate.


## Technology

- Java 21
- Spring Boot 3.4.5
- Spring MVC and Thymeleaf
- Spring Data JPA / Hibernate
- MySQL 8
- Maven
- HTML, CSS, and a small amount of JavaScript
- JUnit 5, Spring Boot Test, and H2 for tests

## Run locally

1. Install Java 21 and MySQL.
2. Create the database:

   ```sql
   CREATE DATABASE smart_queue;
   ```

3. From the project root, configure the connection. Example for a MySQL root account with a password:

   ```bash
   export DB_URL='jdbc:mysql://localhost:3306/smart_queue'
   export DB_USERNAME='root'
   read -s "DB_PASSWORD?Enter your MySQL password: "
   echo
   export DB_PASSWORD
   ```

   If your local MySQL account has no password, run `unset DB_PASSWORD` instead.

4. Start the application:

   ```bash
   chmod +x mvnw
   ./mvnw spring-boot:run
   ```

5. Open http://localhost:8080.

The application inserts the predefined service catalogue when it starts. Hibernate updates the development schema using the entity mappings.

## Useful pages

- `/` — project home
- `/register` — customer registration
- `/track` — look up an existing token
- `/display` — public queue display
- `/token/{number}` — token details

## Queue logic

Waiting tokens are sorted by priority rank (smaller rank means higher priority), then creation time, then database ID. This means priority takes precedence over arrival time, while customers within the same priority category retain FIFO order.

The waiting-time value is an estimate, not a prediction. It sums the configured average service durations for customers ahead in the queue and includes a simple allowance for any currently serving token. Actual wait times can differ.
