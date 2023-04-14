package com.project.publisher.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SurveyResponse extends PublicationResponse {
    private String title;
    private List<QuestionResponse> questionnaire;
}
