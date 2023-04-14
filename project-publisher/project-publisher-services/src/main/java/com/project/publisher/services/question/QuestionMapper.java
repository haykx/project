package com.project.publisher.services.question;

import com.project.publisher.entities.Question;
import com.project.publisher.request.QuestionRequest;
import com.project.publisher.response.QuestionResponse;
import com.project.publisher.services.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper implements Mapper<QuestionRequest, Question, QuestionResponse> {
    @Override
    public Question toEntity(QuestionRequest request) {
        Question question = new Question();
        question.setType(request.getType());
        question.setQuestion(request.getQuestion());
        question.setOptions(request.getOptions());
        return question;
    }

    @Override
    public QuestionResponse toResponse(Question question) {
        QuestionResponse response = new QuestionResponse();
        response.setId(question.getId());
        response.setType(question.getType());
        response.setQuestion(question.getQuestion());
        response.setOptions(question.getOptions());
        response.setCreated(question.getCreated());
        response.setUpdated(question.getUpdated());
        return response;
    }
}
