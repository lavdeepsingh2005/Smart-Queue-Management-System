# Smart Queue Management System

This project is a Java-based queue management system designed for places like hospitals, banks, and college offices, where customers often have to wait for their turn.

The idea is to make the process easier by generating tokens and arranging customers based on their priority. I've started with the basic queue functionality and will be adding the staff-side features in later stages.

## Implementation

The current version includes:

- Customer registration with basic input validation.
- A list of predefined services that customers can choose from.
- Automatic token generation using the priority, service code, and database ID.
- Three priority categories: Emergency/VIP, Assisted, and Regular.
- Queue arrangement based on priority, while maintaining first-come-first-served order within each category.
- A page where customers can check their token details and queue position.
- A public page showing the current waiting queue, which refreshes periodically.
- A basic waiting-time estimate using the average time assigned to each service.
- MySQL database integration for storing customer and token details.
  

## Technologies 

- Java 21
- Spring Boot 3.4.5
- Spring MVC
- Thymeleaf
- Spring Data JPA and Hibernate
- MySQL 8
- Maven
- HTML, CSS and JavaScript
- JUnit 5 and H2 for testing

## Running the project

Make sure Java 21 and MySQL are installed before starting.

**1. Create the database**

Open MySQL and run:

```sql
CREATE DATABASE smart_queue;
```

**2. Open the project folder**

Navigate to the project directory in Terminal.

**3. Set up the database connection**

For a MySQL root account with a password, run:

```bash
export DB_URL='jdbc:mysql://localhost:3306/smart_queue'
export DB_USERNAME='root'
read -s "DB_PASSWORD?Enter your MySQL password: "
echo
export DB_PASSWORD
```

If your MySQL root account does not have a password, use:

```bash
unset DB_PASSWORD
```

**4. Start the application**

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

**5. Open the application**

Visit [http://localhost:8080](http://localhost:8080) in your browser.

The predefined services are added automatically when the application starts. Hibernate also updates the development database schema based on the entity classes.

## Application pages

| Page | URL |
|---|---|
| Home | `/` |
| Customer registration | `/register` |
| Token tracking | `/track` |
| Public queue display | `/display` |
| Token details | `/token/{number}` |

## How the queue works

Each token is assigned a priority when it is generated.

The system uses three priority levels:

1. Emergency/VIP
2. Assisted
3. Regular

Customers with a higher priority are placed ahead of those with a lower priority. If two customers have the same priority, the one who registered first is placed ahead.

The system also uses the database ID as a final tie-breaker when two tokens have the same creation time.

Waiting time is calculated using the average service duration configured for each service. The estimated time includes the customers ahead in the queue and a small allowance for a customer who is currently being served.

These values are only estimates, since actual service times can vary.

