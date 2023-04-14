package com.project.publisher.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class SurveyRequest {
    @NotNull
    private UUID publisherId;
    @NotNull
    @NotEmpty
    @NotBlank
    private String title;

    @NotNull
    @NotEmpty
    @NotBlank
    private String body;

    @NotNull
    private List<QuestionRequest> questionnaire;
}
