CREATE TABLE t_like_discussion
(
    publisher_id UUID NOT NULL REFERENCES publisher.t_publishers (id) ON UPDATE CASCADE ON DELETE CASCADE,
    discussion_id UUID NOT NULL REFERENCES publisher.t_discussions (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE t_like_comment
(
    publisher_id UUID NOT NULL REFERENCES publisher.t_publishers (id) ON UPDATE CASCADE ON DELETE CASCADE,
    comment_id UUID NOT NULL REFERENCES publisher.t_comments (id) ON UPDATE CASCADE ON DELETE CASCADE
);