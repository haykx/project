package com.project.publisher.request;

import lombok.Data;

import java.util.HashMap;
import java.util.UUID;

@Data
public class SurveyParticipantRequest {
    private UUID publisherId;
    private UUID surveyId;
    private HashMap<UUID, String> answers;

}
