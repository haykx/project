package com.project.publisher.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.publisher.entities.Publisher;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.net.URL;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostResponse extends BaseResponse {
    private String headline;
    private byte[] image;
    private String body;
    private URL link;
    @JsonIgnore
    private Publisher publisher;
}
