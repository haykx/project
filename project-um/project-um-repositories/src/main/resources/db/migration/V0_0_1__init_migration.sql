CREATE TABLE t_permissions
(
    id      UUID        NOT NULL,
    name    VARCHAR(50) NOT NULL,
    created TIMESTAMP   NOT NULL,
    updated TIMESTAMP   NOT NULL,
    deleted TIMESTAMP,

    PRIMARY KEY (id)
);
CREATE TABLE t_roles
(
    id      UUID        NOT NULL,
    name    VARCHAR(50) NOT NULL,
    created TIMESTAMP   NOT NULL,
    updated TIMESTAMP   NOT NULL,
    deleted TIMESTAMP,

    PRIMARY KEY (id)
);
CREATE TABLE t_roles_permissions
(
    role_id       UUID NOT NULL REFERENCES t_roles (id) ON UPDATE CASCADE ON DELETE CASCADE,
    permission_id UUID NOT NULL REFERENCES t_permissions (id) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE t_publisher
(
    id         UUID        NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    password   VARCHAR(70) NOT NULL,
    bio        TEXT        NOT NULL,
    avatar     BYTEA,
    created    TIMESTAMP   NOT NULL,
    updated    TIMESTAMP   NOT NULL,
    deleted    TIMESTAMP,

    PRIMARY KEY (id)
);
CREATE TABLE t_publishers_roles
(
    publisher_id UUID NOT NULL REFERENCES t_publisher (id) ON UPDATE CASCADE ON DELETE CASCADE,
    role_id       UUID NOT NULL REFERENCES t_roles (id) ON UPDATE CASCADE ON DELETE CASCADE
);