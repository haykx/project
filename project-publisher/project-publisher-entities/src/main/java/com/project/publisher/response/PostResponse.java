package com.project.publisher.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostResponse extends BaseResponse {
    private String headline;
    private String image;
    private String body;
    private String link;
    private int likes;
    private UUID publisherId;
}
