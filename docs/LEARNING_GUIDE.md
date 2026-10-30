# Phase 1 Learning Guide

## Java concepts
- Classes and objects: `Customer`, `ServiceType`, and `QueueToken` represent application data.
- Encapsulation: entity fields are private and accessed through methods.
- Enums: `Priority` and `TokenStatus` restrict values to defined constants.
- Interfaces: repository interfaces extend `JpaRepository`.
- Collections and streams: waiting tokens are sorted and processed as Java collections.
- Exceptions: missing token lookups are reported using `IllegalArgumentException`.

## Spring concepts
- `@SpringBootApplication` starts the application and enables component scanning and auto-configuration.
- `@Controller` handles browser requests and returns Thymeleaf view names.
- `@Service` marks business-logic components.
- Constructor injection supplies repositories and services to classes.
- `@Transactional` groups customer and token creation into one database transaction.
- `@Valid` and Bean Validation annotations validate customer input on the server.

## Database concepts
- JPA entities map Java classes to database tables.
- `@Id` defines primary keys; `@GeneratedValue` delegates ID generation to MySQL.
- `@ManyToOne` links a queue token to one customer and one service type.
- `@Enumerated(EnumType.STRING)` stores enum names as readable strings.
- Spring Data JPA derives queries from repository method names.

## Queue algorithm
The waiting list is sorted by priority rank, creation timestamp, and database ID. A lower rank is a higher priority. The latter two fields keep the order stable for customers in the same priority category.

## Waiting-time estimate
For a waiting token, the application adds the configured average service duration of each token ahead of it. It adds a simple allowance for a token currently being served. This is a basic estimate and does not learn from historical service data.
