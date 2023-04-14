package com.project.publisher.request;

import com.project.publisher.enums.QuestionType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class QuestionRequest {
    @NotNull
    private QuestionType type;
    @NotNull
    private String question;
    private List<String> options;
}
