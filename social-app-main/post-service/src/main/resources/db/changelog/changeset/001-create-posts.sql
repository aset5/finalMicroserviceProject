CREATE TABLE posts (
    id UUID PRIMARY KEY,
    author_id UUID NOT NULL,

    content TEXT NOT NULL,
    post_status VARCHAR(20) NOT NULL,

    published_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);