package com.project.publisher.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PublisherResponse extends BaseResponse {
    private String firstName;
    private String lastName;
    private String bio;
    private byte[] avatar;
    private List<DiscussionResponse> posts;
}
