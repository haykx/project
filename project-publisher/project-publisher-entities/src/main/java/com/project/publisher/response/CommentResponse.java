package com.project.publisher.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentResponse extends BaseResponse {

    private String text;
    private int likes;
    private PublisherResponse publisher;
}
