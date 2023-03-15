package com.project.publisher.repositories;

import com.project.publisher.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID>, JpaSpecificationExecutor<Comment> {
    @Query("SELECT c FROM Comment c WHERE c.id = :id")
    Optional<Comment> findById(@Param("id") UUID id);

    @Modifying
    @Query("UPDATE Comment c SET c.likes=c.likes+1 WHERE c.id = :id")
    void like(@Param("id") UUID id);

    @Modifying
    @Query("UPDATE Comment c SET c.likes=c.likes-1 WHERE c.id = :id")
    void unlike(@Param("id") UUID id);
}
