package com.project.publisher.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class DiscussionRequest {
    @NotNull
    private UUID publisherId;
    @NotNull
    private String question;
    @NotNull
    private String body;
}
