CREATE DATABASE IF NOT EXISTS smart_queue CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- Create a dedicated local user if you do not want to use your existing MySQL account.
-- Run these statements as a MySQL administrator, changing the password first:
-- CREATE USER 'smartqueue'@'localhost' IDENTIFIED BY 'change_me';
-- GRANT ALL PRIVILEGES ON smart_queue.* TO 'smartqueue'@'localhost';
-- FLUSH PRIVILEGES;
-- Hibernate creates or updates the tables on startup using the entity classes.
