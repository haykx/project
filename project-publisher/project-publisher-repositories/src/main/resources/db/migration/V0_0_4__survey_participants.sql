CREATE TABLE t_surveys_participants
(
    id             UUID      NOT NULL,
    participant_id UUID      NOT NULL REFERENCES publisher.t_publishers (id),
    survey_id      UUID      NOT NULL REFERENCES publisher.t_surveys (id),
    answers        JSONB     NOT NULL,
    created        TIMESTAMP NOT NULL,
    updated        TIMESTAMP NOT NULL,

    PRIMARY KEY (id)
);