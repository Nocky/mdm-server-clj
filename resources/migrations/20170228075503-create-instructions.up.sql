CREATE TABLE IF NOT EXISTS instructions (id SERIAL PRIMARY KEY,
                                    status VARCHAR(40) NOT NULL,
                                    instruction VARCHAR(1000),
                                    device_id INTEGER NOT NULL);
