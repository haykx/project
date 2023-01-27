package com.project.publisher.request;

import lombok.Data;

import java.util.UUID;

@Data
public class PostRequest {
    private UUID publisherId;
    private String headline;

    private byte[] image;
    private String body;
    private String link;
}
