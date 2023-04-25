package com.project.entities.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UmPublisherResponse extends BaseResponse {
    private String email;
}
