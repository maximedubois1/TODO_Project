-- DELETE data from table
DELETE FROM users;

-- Reset sequence
ALTER SEQUENCE users_id_seq RESTART WITH 1;
