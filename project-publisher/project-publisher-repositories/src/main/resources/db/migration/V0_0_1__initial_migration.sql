CREATE table t_publishers
(
    id          UUID         NOT NULL,
    original_id UUID         NOT NULL UNIQUE,
    first_name  VARCHAR(50)  NOT NULL,
    last_name   VARCHAR(50)  NOT NULL,
    bio         VARCHAR(100) NOT NULL,
    created     TIMESTAMP    NOT NULL,
    updated     TIMESTAMP    NOT NULL,

    PRIMARY KEY (id)
);
CREATE TABLE t_discussions
(
    id           UUID          NOT NULL,
    question     VARCHAR(150)  NOT NULL,
    body         VARCHAR(2000) NOT NULL,
    likes        INT           NOT NULL,
    publisher_id UUID          NOT NULL REFERENCES t_publishers (id) ON UPDATE CASCADE ON DELETE CASCADE,
    created      TIMESTAMP     NOT NULL,
    updated      TIMESTAMP     NOT NULL,

    PRIMARY KEY (id)
);
CREATE TABLE t_comments
(
    id           UUID         NOT NULL,
    text         VARCHAR(300) NOT NULL,
    discussion_id      UUID REFERENCES t_discussions (id) ON UPDATE CASCADE ON DELETE CASCADE,
    publisher_id UUID REFERENCES t_publishers (id) ON UPDATE CASCADE ON DELETE CASCADE,
    parent_id    UUID REFERENCES t_comments (id) ON UPDATE CASCADE ON DELETE CASCADE,
    likes        INT          NOT NULL,
    created      TIMESTAMP    NOT NULL,
    updated      TIMESTAMP    NOT NULL,

    PRIMARY KEY (id),
    CHECK ( (discussion_id IS NULL AND parent_id IS NOT NULL) OR (discussion_id IS NOT NULL AND parent_id IS NULL) )
);