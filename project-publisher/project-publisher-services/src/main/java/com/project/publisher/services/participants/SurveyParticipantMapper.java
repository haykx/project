package com.project.publisher.services.participants;

import com.project.publisher.entities.Question;
import com.project.publisher.entities.SurveyParticipants;
import com.project.publisher.repositories.QuestionRepository;
import com.project.publisher.request.SurveyParticipantRequest;
import com.project.publisher.response.QuestionResponse;
import com.project.publisher.response.SurveyParticipantResponse;
import com.project.publisher.services.mapper.Mapper;
import com.project.publisher.services.question.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SurveyParticipantMapper implements Mapper<SurveyParticipantRequest, SurveyParticipants, SurveyParticipantResponse> {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public SurveyParticipants toEntity(SurveyParticipantRequest request) {
        SurveyParticipants entity = new SurveyParticipants();
        entity.setAnswers(this.mapAnswersToEntity(request.getAnswers()));
        entity.setParticipantId(request.getPublisherId());
        entity.setSurveyId(request.getSurveyId());
        return entity;
    }

    @Override
    public SurveyParticipantResponse toResponse(SurveyParticipants entity) {
        SurveyParticipantResponse response = new SurveyParticipantResponse();
        response.setId(entity.getId());
        response.setAnswers(this.mapAnswersToResponse(entity.getAnswers()));
        response.setPublisherId(entity.getParticipantId());
        response.setSurveyId(entity.getSurveyId());
        response.setCreated(entity.getCreated());
        response.setUpdated(entity.getUpdated());
        return response;
    }

    private HashMap<Question, String> mapAnswersToEntity(HashMap<UUID, String> map) {
        HashMap<Question, String> res = new HashMap<>();
        map.forEach((key, value) -> res.put(this.questionRepository.findById(key).orElseThrow(), value));
        return res;
    }

    private HashMap<QuestionResponse, String> mapAnswersToResponse(HashMap<Question, String> map) {
        HashMap<QuestionResponse, String> res = new HashMap<>();
        map.forEach((key, value) -> res.put(this.questionMapper.toResponse(key), value));
        return res;
    }
}
