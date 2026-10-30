-- Optional example services. The application creates these if they are missing.
INSERT INTO service_types (name, code, average_minutes, active) VALUES
('General Consultation', 'GC', 15, true),
('Billing and Payments', 'BP', 8, true),
('Document Verification', 'DV', 10, true),
('Student Administration', 'SA', 12, true),
('General Enquiry', 'GE', 5, true);
-- Run only if the service codes do not already exist. Duplicate codes are not allowed.
