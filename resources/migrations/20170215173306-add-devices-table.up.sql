
CREATE TABLE IF NOT EXISTS devices (id SERIAL PRIMARY KEY,
                                    unique_id VARCHAR(40) UNIQUE NOT NULL,
                                    model VARCHAR(40),
                                    imei_number VARCHAR(40),
                                    os_version VARCHAR(40),
                                    access_token VARCHAR(40),
                                    client_version VARCHAR(40),
                                    last_seen TIMESTAMP WITH TIME ZONE,
                                    created_at TIMESTAMP WITH TIME ZONE,
                                    updated_at TIMESTAMP WITH TIME ZONE);
