package com.project.publisher.request;

import lombok.Data;

@Data
public class PostUpdateDto {
    private String headline;
    private byte[] image;
    private String body;
    private String link;
}
