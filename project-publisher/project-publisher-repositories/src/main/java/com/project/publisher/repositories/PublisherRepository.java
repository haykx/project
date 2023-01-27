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
    Optional<Publisher> findByIdAndDeletedIsNull(UUID id);

    @Modifying
    @Query("UPDATE Publisher p SET p.deleted = CURRENT_TIMESTAMP WHERE p.id = :id AND p.deleted IS NULL")
    void delete(@Param("id")UUID id);
}
