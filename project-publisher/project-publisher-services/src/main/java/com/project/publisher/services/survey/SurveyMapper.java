package com.project.publisher.services.survey;

import com.project.publisher.entities.Publisher;
import com.project.publisher.entities.Survey;
import com.project.publisher.repositories.PublisherRepository;
import com.project.publisher.repositories.QuestionRepository;
import com.project.publisher.request.SurveyRequest;
import com.project.publisher.response.SurveyResponse;
import com.project.publisher.services.mapper.Mapper;
import com.project.publisher.services.question.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SurveyMapper implements Mapper<SurveyRequest, Survey, SurveyResponse> {

    private final PublisherRepository publisherRepository;
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;

    @Override
    public Survey toEntity(SurveyRequest request) {
        Survey survey = new Survey();
        survey.setPublisher(this.getPublisher(request));
        survey.setTitle(request.getTitle());
        survey.setBody(request.getBody());
        survey.setQuestionnaire(request.getQuestionnaire().stream().map(q -> this.questionRepository.save(this.questionMapper.toEntity(q))).collect(Collectors.toList()));
        return survey;
    }

    @Override
    public SurveyResponse toResponse(Survey survey) {
        SurveyResponse response = new SurveyResponse();
        response.setId(survey.getId());
        response.setQuestionnaire(survey.getQuestionnaire().stream().map(questionMapper::toResponse).collect(Collectors.toList()));
        response.setTitle(survey.getTitle());
        response.setCreated(survey.getCreated());
        response.setUpdated(survey.getUpdated());
        return response;
    }

    private Publisher getPublisher(final SurveyRequest request) {
        return this.publisherRepository.findById(request.getPublisherId()).orElseThrow();
    }
}
