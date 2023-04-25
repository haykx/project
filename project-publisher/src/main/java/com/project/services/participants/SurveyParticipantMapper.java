package com.project.services.participants;

import com.project.entities.model.SurveyParticipants;
import com.project.repositories.PublisherRepository;
import com.project.repositories.QuestionRepository;
import com.project.entities.request.SurveyParticipantRequest;
import com.project.entities.response.QuestionResponse;
import com.project.entities.response.SurveyParticipantResponse;
import com.project.services.mapper.Mapper;
import com.project.services.question.QuestionMapper;
import com.project.services.token.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SurveyParticipantMapper implements Mapper<SurveyParticipantRequest, SurveyParticipants, SurveyParticipantResponse> {

    private final QuestionRepository questionRepository;
    private final AuthFacade facade;
    private final QuestionMapper questionMapper;
    private final PublisherRepository publisherRepository;

    @Override
    public SurveyParticipants toEntity(SurveyParticipantRequest request) {
        SurveyParticipants entity = new SurveyParticipants();
        entity.setParticipantId(this.publisherRepository.getIdByOriginalId(facade.getOriginalId()));
        entity.setAnswers(request.getAnswers());
        entity.setSurveyId(request.getSurveyId());
        return entity;
    }

    @Override
    public SurveyParticipantResponse toResponse(SurveyParticipants entity) {
        SurveyParticipantResponse response = new SurveyParticipantResponse();
        response.setId(entity.getId());
        response.setAnswers(this.mapAnswers(entity.getAnswers()));
        response.setPublisherId(entity.getParticipantId());
        response.setSurveyId(entity.getSurveyId());
        response.setCreated(entity.getCreated());
        response.setUpdated(entity.getUpdated());
        return response;
    }

    private HashMap<QuestionResponse, String> mapAnswers(HashMap<UUID, String> map) {
        HashMap<QuestionResponse, String> res = new HashMap<>();
        map.forEach((key, value) -> res.put(this.questionMapper.toResponse(this.questionRepository.findById(key).orElseThrow()), value));
        return res;
    }
}
