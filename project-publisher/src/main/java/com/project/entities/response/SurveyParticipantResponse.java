package com.project.entities.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class SurveyParticipantResponse extends BaseResponse {
    private UUID publisherId;
    private UUID surveyId;
    private HashMap<QuestionResponse, String> answers;

}
