package com.project.publisher.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.publisher.entities.Publisher;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostResponse extends BaseResponse {
    private String headline;
    private byte[] image;
    private String body;
    private String link;
    @JsonIgnore
    private Publisher publisher;
}
