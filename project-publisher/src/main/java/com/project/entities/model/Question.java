package com.project.entities.model;

import com.project.entities.enums.QuestionType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_questions")
public class Question extends BaseEntity {
    @Enumerated
    @Column(name = "type")
    private QuestionType type;
    @Column(name = "question")
    private String question;

    @Column(name = "required")
    private boolean required;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @ElementCollection
    @CollectionTable(name = "t_options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "options")
    private List<String> options = new ArrayList<>();

}
