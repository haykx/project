package com.project.publisher.request;

import lombok.Data;

@Data
public class PublisherRequest {
    private String firstName;
    private String lastName;
    private String bio;
    private byte[] avatar;
}
