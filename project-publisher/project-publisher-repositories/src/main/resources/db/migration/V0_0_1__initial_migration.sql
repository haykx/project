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
    deleted     TIMESTAMP,

    PRIMARY KEY (id)
);
CREATE TABLE t_posts
(
    id           UUID          NOT NULL,
    headline     VARCHAR(150)   NOT NULL,
    image        BYTEA         NOT NULL,
    body         VARCHAR(7000) NOT NULL,
    link         VARCHAR(150),
    publisher_id UUID          NOT NULL REFERENCES t_publishers (id) ON UPDATE CASCADE ON DELETE CASCADE,
    created      TIMESTAMP     NOT NULL,
    updated      TIMESTAMP     NOT NULL,
    deleted      TIMESTAMP,

    PRIMARY KEY (id)
);