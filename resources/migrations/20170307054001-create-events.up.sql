CREATE TABLE IF NOT EXISTS events (id SERIAL PRIMARY KEY,
                                    name VARCHAR(40) NOT NULL,
                                    payload VARCHAR(1000),
                                    device_id INTEGER NOT NULL);
