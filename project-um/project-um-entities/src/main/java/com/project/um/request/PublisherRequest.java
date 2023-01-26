package com.project.um.request;

import lombok.Data;

@Data
public class PublisherRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String bio;
    private byte[] avatar;
}
