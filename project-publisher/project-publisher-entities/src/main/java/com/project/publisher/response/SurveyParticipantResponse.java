package com.project.publisher.response;

import lombok.Data;

import java.util.HashMap;
import java.util.UUID;

@Data
public class SurveyParticipantResponse extends BaseResponse {
    private UUID publisherId;
    private UUID surveyId;
    private HashMap<QuestionResponse, String> answers;

}
