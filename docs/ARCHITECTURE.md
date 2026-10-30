# Phase 1 Architecture

The application uses a conventional Spring MVC layered structure.

1. **Presentation:** Thymeleaf templates, HTML, CSS, and small browser-side JavaScript.
2. **Controller:** `HomeController` and `CustomerController` handle page requests and form submissions.
3. **Service:** `RegistrationService` coordinates customer and token creation in one transaction; `TokenService` generates the token number; `QueueService` and `WaitingTimeService` retrieve and order the queue and calculate estimates.
4. **Repository:** Spring Data JPA repositories provide persistence operations.
5. **Model:** JPA entities represent customers, services, and queue tokens. Enums define priority and token status.
6. **Database:** MySQL stores the records. Hibernate maps entities to tables.

## Registration flow

Browser form → `CustomerController` → `RegistrationService` → `CustomerRepository` and `TokenService` → `QueueTokenRepository` → MySQL → token confirmation page.

`RegistrationService.register()` is transactional so the customer and their token are created as one unit of work. `TokenService` first persists the token to obtain its generated database ID, constructs the readable token number, and saves the updated token.

## Queue ordering

`WaitingTimeService` retrieves tokens with `WAITING` status and sorts them by priority rank, creation timestamp, and ID. Lower priority rank values are served first. Timestamp and ID preserve FIFO ordering within a priority category.

## Current limitations

- No staff-side queue controls in this milestone.
- No database-level locking for simultaneous queue administration; this is planned before adding concurrent staff operations.
- Customer-selected priority is not independently verified.
- The estimate uses configured average service times rather than measured historical data.
- The public token number contains a sequential database ID and should be replaced with a random public identifier before production use.
