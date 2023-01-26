package com.project.publisher.request;

import lombok.Data;

import java.net.URL;

@Data
public class PostUpdateDto {
    private String headline;
    private byte[] image;
    private String body;
    private URL link;
}
