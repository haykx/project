package com.project.entities.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class DiscussionResponse extends PublicationResponse {
    private String question;
    private String body;
    private int likes;
    private UUID publisherId;
    private String publisherName;
    private List<CommentResponse> comments;
    private boolean isLiked;
}
