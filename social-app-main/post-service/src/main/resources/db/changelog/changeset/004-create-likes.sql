CREATE TABLE likes (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,

    content_id UUID NOT NULL,
    content_type VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    CONSTRAINT uq_likes_user_content UNIQUE (user_id, content_id, content_type)
);