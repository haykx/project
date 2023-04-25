package com.project.entities.response;

import com.project.entities.enums.QuestionType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionResponse extends BaseResponse {

    private QuestionType type;
    private String question;
    private List<String> options;
    private boolean required;
}
