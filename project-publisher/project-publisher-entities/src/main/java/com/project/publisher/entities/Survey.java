package com.project.publisher.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "t_surveys")
public class Survey extends Publication {
    @Column(name = "title")
    private String title;
    @Column(name = "body")
    private String body;
    @OneToMany(mappedBy = "survey", orphanRemoval = true)
    private List<Question> questionnaire = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

}
