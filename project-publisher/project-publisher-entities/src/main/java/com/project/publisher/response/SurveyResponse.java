package com.project.publisher.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class SurveyResponse extends PublicationResponse {
    private String title;
    private String body;
    private List<QuestionResponse> questionnaire;
    private UUID publisherId;
}
