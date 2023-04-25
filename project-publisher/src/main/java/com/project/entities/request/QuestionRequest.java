package com.project.entities.request;

import com.project.entities.enums.QuestionType;
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
    private boolean required;
}
