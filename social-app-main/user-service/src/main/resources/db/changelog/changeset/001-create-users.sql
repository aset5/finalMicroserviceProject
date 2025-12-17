CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    phone_number VARCHAR(50),
    preferred_contact VARCHAR(20) NOT NULL,
    tariff_type VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);

