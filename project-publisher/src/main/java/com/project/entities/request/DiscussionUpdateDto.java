package com.project.entities.request;

import lombok.Data;

@Data
public class DiscussionUpdateDto {
    private String headline;
    private byte[] image;
    private String body;
    private String link;
}
