CREATE TABLE user_profiles (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL UNIQUE,

    username VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100),
    bio TEXT NOT NULL,

    account_status VARCHAR(20) NOT NULL,
    avatar_file_id UUID,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);