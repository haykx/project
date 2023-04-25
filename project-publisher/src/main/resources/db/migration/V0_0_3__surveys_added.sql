CREATE TABLE t_surveys
(
    id           UUID          NOT NULL,
    title        VARCHAR(255)  NOT NULL,
    body         VARCHAR(1000) NOT NULL,
    publisher_id UUID          NOT NULL REFERENCES project.t_publishers (id) ON UPDATE CASCADE ON DELETE CASCADE,
    created      TIMESTAMP     NOT NULL,
    updated      TIMESTAMP     NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE t_questions
(
    id        UUID         NOT NULL,
    question  VARCHAR(255) NOT NULL,
    required  BOOLEAN      NOT NULL,
    survey_id UUID         NOT NULL REFERENCES t_surveys (id) ON UPDATE CASCADE ON DELETE CASCADE,
    created   TIMESTAMP    NOT NULL,
    updated   TIMESTAMP    NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE t_options
(
    question_id UUID NOT NULL REFERENCES t_questions (id),
    options     VARCHAR(1500)
);

CREATE TABLE t_surveys_participants
(
    id             UUID      NOT NULL,
    survey_id      UUID      NOT NULL REFERENCES t_surveys (id) ON UPDATE CASCADE ON DELETE CASCADE,
    participant_id UUID      NOT NULL REFERENCES project.t_publishers (id) ON UPDATE CASCADE ON DELETE CASCADE,
    answers        JSON      NOT NULL,
    created        TIMESTAMP NOT NULL,
    updated        TIMESTAMP NOT NULL,

    PRIMARY KEY (id)
);