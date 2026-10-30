# Smart Queue & Appointment Management System

A Java-based queue management system that allows customers to register for services, receive tokens, and check their queue position and estimated waiting time.

The project focuses on making queue management simpler through automatic token generation and priority-based processing.

## Features implemented

- Customer registration and service selection
- Automatic token generation
- Three priority categories: Emergency/VIP, Senior Citizen/Assisted, and Regular
- Priority-based queue ordering
- FIFO processing within each priority category
- Token tracking
- Queue position and estimated waiting time
- MySQL database integration

## Technologies 

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Thymeleaf
- HTML, CSS and JavaScript

## Running the project locally

### 1. Check Java installation

Make sure Java 21 is installed. Check the version in Terminal:

```bash
java -version
```

### 2. Open the project directory

Navigate to the folder where the project is saved:

```bash
cd ~/Downloads/Smart-Queue-Management-System-Phase1
```

### 3. Configure the database connection

Set the database connection details in Terminal:

```bash
export DB_URL='jdbc:mysql://localhost:3306/smart_queue'
export DB_USERNAME='root'
```

Enter your MySQL root password:

```bash
export DB_PASSWORD='YOUR_MYSQL_PASSWORD'
```

Replace `YOUR_MYSQL_PASSWORD` with your actual MySQL password.

### 4. Start the application

Give the Maven wrapper permission to run:

```bash
chmod +x mvnw
```

Start the application:

```bash
./mvnw spring-boot:run
```

### 5. Open the application

Once the application has started, open the following address in your browser:

**http://localhost:8080**

## Queue management logic

Customers are arranged according to their priority. Emergency/VIP customers are served first, followed by Assisted customers and then Regular customers.

Within each priority category, customers are served in the order in which they registered.

Each token is generated using the customer's priority, selected service, and database-generated ID.

The estimated waiting time is calculated using the number of customers ahead in the queue and the average service duration.

