package com.project.publisher.request;

import lombok.Data;

import java.util.UUID;

@Data
public class DiscussionRequest {
    private UUID publisherId;
    private String question;
    private String image;
    private String body;
    private String link;
}
