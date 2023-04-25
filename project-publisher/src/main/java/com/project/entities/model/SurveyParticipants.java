package com.project.entities.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.HashMap;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_surveys_participants")
public class SurveyParticipants extends BaseEntity {

    @Column(name = "participant_id")
    private UUID participantId;

    @Column(name = "survey_id")
    private UUID surveyId;

    @Type(JsonType.class)
    @Column(name = "answers")
    private HashMap<UUID, String> answers;

}
