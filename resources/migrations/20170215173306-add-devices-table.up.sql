
CREATE TABLE IF NOT EXISTS devices (id SERIAL PRIMARY KEY, model VARCHAR(40), imei_number VARCHAR(40), last_seen DATE);
