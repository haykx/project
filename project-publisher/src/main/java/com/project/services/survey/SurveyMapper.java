package com.project.services.survey;

import com.project.entities.model.Publisher;
import com.project.entities.model.Survey;
import com.project.entities.request.SurveyRequest;
import com.project.entities.response.SurveyResponse;
import com.project.repositories.PublisherRepository;
import com.project.repositories.SurveyParticipantsRepository;
import com.project.services.mapper.Mapper;
import com.project.services.question.QuestionMapper;
import com.project.services.token.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SurveyMapper implements Mapper<SurveyRequest, Survey, SurveyResponse> {

    private final PublisherRepository publisherRepository;
    private final QuestionMapper questionMapper;
    private final AuthFacade facade;
    private final SurveyParticipantsRepository surveyParticipantsRepository;

    @Override
    public Survey toEntity(SurveyRequest request) {
        Survey survey = new Survey();
        survey.setPublisher(this.getPublisher(request));
        survey.setTitle(request.getTitle());
        survey.setBody(request.getBody());
        return survey;
    }

    @Override
    public SurveyResponse toResponse(Survey survey) {
        SurveyResponse response = new SurveyResponse();
        try {
            final UUID pubId = this.publisherRepository.getIdByOriginalId(this.facade.getOriginalId());
            response.setCompleted(surveyParticipantsRepository.existsBySurveyIdAndParticipantId(survey.getId(), pubId));
        } catch (Exception e) {
            response.setCompleted(false);
        }
        response.setId(survey.getId());
        response.setQuestionnaire(survey.getQuestionnaire().stream().map(questionMapper::toResponse).collect(Collectors.toList()));
        response.setTitle(survey.getTitle());
        response.setBody(survey.getBody());
        response.setPublisherId(survey.getPublisher().getId());
        response.setCreated(survey.getCreated());
        response.setUpdated(survey.getUpdated());
        return response;
    }

    private Publisher getPublisher(final SurveyRequest request) {
        return this.publisherRepository.findById(request.getPublisherId()).orElseThrow();
    }
}
