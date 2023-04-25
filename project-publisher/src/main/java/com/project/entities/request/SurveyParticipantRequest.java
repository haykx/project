package com.project.entities.request;

import lombok.Data;

import java.util.HashMap;
import java.util.UUID;

@Data
public class SurveyParticipantRequest {
    private UUID surveyId;
    private HashMap<UUID, String> answers;

}
