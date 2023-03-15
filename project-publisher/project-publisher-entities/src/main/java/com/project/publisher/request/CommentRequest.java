package com.project.publisher.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentRequest {
    private UUID discussionId;
    private UUID commentId;
    private String text;
}
