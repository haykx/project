package com.project.publisher.repositories;

import com.project.publisher.entities.SurveyParticipants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SurveyParticipantsRepository extends JpaRepository<SurveyParticipants, UUID> {
    @Override
    Optional<SurveyParticipants> findById(UUID id);
}
