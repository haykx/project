package com.project.um.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PublisherResponse extends BaseResponse{
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private byte[] avatar;
}
