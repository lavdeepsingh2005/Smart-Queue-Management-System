# Running the Phase 1 Prototype Locally

Requirements: Java 21, MySQL 8, and a terminal.

1. Create the database with `CREATE DATABASE smart_queue;`.
2. Set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` as described in the README.
3. From the project root, run `chmod +x mvnw` and `./mvnw spring-boot:run`.
4. Open `http://localhost:8080`.

The predefined service records are inserted automatically if their codes do not already exist. The app uses `spring.jpa.hibernate.ddl-auto=update` for this development prototype.
