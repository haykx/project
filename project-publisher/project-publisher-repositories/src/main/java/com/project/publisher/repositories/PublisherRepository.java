package com.project.publisher.repositories;

import com.project.publisher.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID> {
    Optional<Publisher> findByIdAndDeletedIsNull(UUID id);
}
