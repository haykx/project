package com.project.publisher.repositories;

import com.project.publisher.entities.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, UUID>, JpaSpecificationExecutor<Discussion> {
    List<Discussion> findAllByIdIn(List<UUID> ids);

    Optional<Discussion> findById(UUID id);

    @Modifying
    @Query("UPDATE Discussion p SET p.likes=p.likes+1, p.updated = CURRENT_TIMESTAMP WHERE p.id = :id")
    void like(@Param("id")UUID id);

    @Modifying
    @Query("UPDATE Discussion p SET p.likes=p.likes-1, p.updated = CURRENT_TIMESTAMP WHERE p.likes > 0 AND p.id = :id")
    void unlike(@Param("id")UUID id);
}
