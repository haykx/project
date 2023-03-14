CREATE table t_publishers
(
    id          UUID         NOT NULL,
    original_id UUID         NOT NULL UNIQUE,
    first_name  VARCHAR(50)  NOT NULL,
    last_name   VARCHAR(50)  NOT NULL,
    bio         VARCHAR(100) NOT NULL,
    avatar      BYTEA,
    created     TIMESTAMP    NOT NULL,
    updated     TIMESTAMP    NOT NULL,

    PRIMARY KEY (id)
);
CREATE TABLE t_posts
(
    id           UUID          NOT NULL,
    question     VARCHAR(150)  NOT NULL,
    body         VARCHAR(2000) NOT NULL,
    link         VARCHAR(150),
    likes        INT           NOT NULL,
    publisher_id UUID          NOT NULL REFERENCES t_publishers (id) ON UPDATE CASCADE ON DELETE CASCADE,
    created      TIMESTAMP     NOT NULL,
    updated      TIMESTAMP     NOT NULL,

    PRIMARY KEY (id)
);
CREATE TABLE t_discussions
(
    id           UUID         NOT NULL,
    text         VARCHAR(300) NOT NULL,
    post_id      UUID REFERENCES t_posts (id) ON UPDATE CASCADE ON DELETE CASCADE,
    publisher_id UUID REFERENCES t_publishers (id) ON UPDATE CASCADE ON DELETE CASCADE,
    parent_id    UUID REFERENCES t_discussions (id) ON UPDATE CASCADE ON DELETE CASCADE,
    likes        INT          NOT NULL,
    created      TIMESTAMP    NOT NULL,
    updated      TIMESTAMP    NOT NULL,

    PRIMARY KEY (id),
    CHECK ( (post_id IS NULL AND parent_id IS NOT NULL) OR (post_id IS NOT NULL AND parent_id IS NULL) )
);