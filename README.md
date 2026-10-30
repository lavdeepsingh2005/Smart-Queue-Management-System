# Smart Queue Management System

A Java-based queue management system that allows customers to register for services, receive tokens, and check their queue position and estimated waiting time.

This is an early version of the project, built to demonstrate token generation and priority-based queue management.

## Features implemented

- Customer registration and service selection
- Automatic token generation
- Three customer priority categories:
  - Emergency / VIP
  - Senior Citizen / Assisted
  - Regular
- Priority-based queue ordering
- FIFO processing within each priority category
- Token tracking
- Queue position and estimated waiting time
- Database storage using H2

## Technologies used

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Thymeleaf
- HTML, CSS and JavaScript

## Running the project locally

You do not need MySQL or any external database installation. Follow these steps.

### 1. Install Java

Make sure Java 21 is installed.

Check your Java version using:

```bash
java -version
```

### 2. Download or clone the project

If you are using GitHub:

```bash
git clone YOUR_REPOSITORY_URL
```

Move into the project directory:

```bash
cd Smart-Queue-Management-System-Phase1
```

If you have already downloaded and extracted the project, simply open Terminal and navigate to its folder.

### 3. Configure the H2 database

The project uses an embedded H2 database. No separate database server is required.

Make sure the H2 dependency is included in `pom.xml` and that `application.properties` is configured to use H2.

Example configuration:

```properties
spring.datasource.url=jdbc:h2:file:./data/smartqueue
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### 4. Run the application

Give the Maven wrapper execution permission:

```bash
chmod +x mvnw
```

Start the application:

```bash
./mvnw spring-boot:run
```

Wait until Spring Boot finishes starting. You should see a message indicating that the application has started.

### 5. Open the application

Open your browser and visit:

**http://localhost:8080**

You can now register customers, generate tokens, and track their queue positions.

### 6. Access the H2 database console (optional)

To inspect the database, open:

**http://localhost:8080/h2-console**

Use the following connection details:

- JDBC URL: `jdbc:h2:file:./data/smartqueue`
- Username: `sa`
- Password: Leave blank

Click **Connect** to view the database tables and stored records.

## Queue management logic

Customers are arranged according to their priority:

1. Emergency / VIP
2. Senior Citizen / Assisted
3. Regular

Customers belonging to the same priority category are processed in the order in which they registered.

The system generates a unique token using the customer's priority, selected service, and database-generated ID.
