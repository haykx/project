package com.project.publisher.repositories;

import com.project.publisher.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID>, JpaSpecificationExecutor<Publisher> {
    boolean existsByOriginalId(UUID id);

    Optional<Publisher> findByOriginalId(UUID id);
}
