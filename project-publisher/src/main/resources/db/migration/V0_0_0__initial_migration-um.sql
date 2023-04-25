CREATE TABLE t_permissions
(
    id      UUID        NOT NULL,
    name    VARCHAR(50) NOT NULL,
    created TIMESTAMP   NOT NULL,
    updated TIMESTAMP   NOT NULL,

    PRIMARY KEY (id)
);
CREATE TABLE t_roles
(
    id      UUID        NOT NULL,
    name    VARCHAR(50) NOT NULL,
    created TIMESTAMP   NOT NULL,
    updated TIMESTAMP   NOT NULL,

    PRIMARY KEY (id)
);
CREATE TABLE t_roles_permissions
(
    role_id       UUID NOT NULL REFERENCES t_roles (id) ON UPDATE CASCADE ON DELETE CASCADE,
    permission_id UUID NOT NULL REFERENCES t_permissions (id) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE t_um_publishers
(
    id       UUID        NOT NULL,
    email    VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(70) NOT NULL,
    created  TIMESTAMP   NOT NULL,
    updated  TIMESTAMP   NOT NULL,

    PRIMARY KEY (id)
);
CREATE TABLE t_publishers_roles
(
    publisher_id UUID NOT NULL REFERENCES t_um_publishers (id) ON UPDATE CASCADE ON DELETE CASCADE,
    role_id      UUID NOT NULL REFERENCES t_roles (id) ON UPDATE CASCADE ON DELETE CASCADE
);