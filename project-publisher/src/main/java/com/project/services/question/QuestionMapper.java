package com.project.services.question;

import com.project.entities.model.Question;
import com.project.entities.request.QuestionRequest;
import com.project.entities.response.QuestionResponse;
import com.project.services.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper implements Mapper<QuestionRequest, Question, QuestionResponse> {
    @Override
    public Question toEntity(QuestionRequest request) {
        Question question = new Question();
        question.setType(request.getType());
        question.setQuestion(request.getQuestion());
        question.setOptions(request.getOptions());
        question.setRequired(request.isRequired());
        return question;
    }

    @Override
    public QuestionResponse toResponse(Question question) {
        QuestionResponse response = new QuestionResponse();
        response.setId(question.getId());
        response.setType(question.getType());
        response.setQuestion(question.getQuestion());
        response.setOptions(question.getOptions());
        response.setRequired(question.isRequired());
        response.setCreated(question.getCreated());
        response.setUpdated(question.getUpdated());
        return response;
    }
}
