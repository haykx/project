package com.project.repositories;

import com.project.entities.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, UUID>, JpaSpecificationExecutor<Survey> {
    @Override
    Optional<Survey> findById(UUID id);
}
