# Database notes

MySQL is the normal database for this prototype. Hibernate maps the entity classes to tables and updates the development schema at startup (`spring.jpa.hibernate.ddl-auto=update`). This is convenient during development, but schema changes should be reviewed carefully for a long-lived deployment.

The Phase 1 entities represent these tables:

- `customers`: customer name, optional phone, and creation time.
- `service_types`: service name, unique short code, average minutes, and active flag.
- `queue_tokens`: unique token number, customer reference, service reference, priority, status, and timestamps.

Each queue token references one customer and one service. Tokens are retained as records rather than being deleted when their status changes. Use fictional customer details during demonstrations.
