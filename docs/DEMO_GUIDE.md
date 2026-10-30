# Phase 1 Demonstration Guide

## Suggested demonstration

1. Open the home page and explain the queue problem being addressed.
2. Register a Regular customer and note the generated token.
3. Register an Emergency/VIP customer for a service.
4. Open the public queue page and show that the higher-priority token is listed first.
5. Register another Regular customer and show that FIFO ordering is preserved within the Regular category.
6. Open a token confirmation page and explain the position and estimated waiting time.

## Explain the implementation

- `CustomerController`: receives registration and tracking requests.
- `RegistrationService`: saves the customer and creates their token within a transaction.
- `TokenService`: creates the token number after the database generates the token ID.
- `WaitingTimeService`: orders waiting tokens and estimates the wait.
- Repositories: persist and retrieve records through Spring Data JPA.
- Thymeleaf: renders server-side HTML using values supplied by controllers.

## Be clear about scope

This is the initial prototype. Staff login, calling the next customer, completing/skipping tokens, and service administration are planned for the next milestone. The public display refreshes periodically; it is not a WebSocket-based live update. Waiting times are estimates based on configured average service durations.
