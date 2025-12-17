CREATE TABLE file_objects (
    id UUID PRIMARY KEY,

    original_file_name VARCHAR(255) NOT NULL,
    file_type VARCHAR(50) NOT NULL,
    quality VARCHAR(20) NOT NULL,

    size BIGINT NOT NULL,
    storage_path VARCHAR(500) NOT NULL,

    created_at TIMESTAMP NOT NULL
);