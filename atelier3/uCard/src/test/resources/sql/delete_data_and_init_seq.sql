-- DELETE data from table
DELETE FROM card;

-- Reset sequence
ALTER SEQUENCE card_id_seq RESTART WITH 1;
