CREATE TABLE post_media (
    post_id UUID NOT NULL,
    file_id UUID NOT NULL,
    PRIMARY KEY (post_id, file_id)
);