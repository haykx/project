package com.project.publisher.repositories;

import com.project.publisher.entities.Post;
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
public interface PostRepository extends JpaRepository<Post, UUID>, JpaSpecificationExecutor<Post> {
    Optional<Post> findByIdAndDeletedIsNull(UUID id);
    List<Post> findAllByIdInAndDeletedIsNull(List<UUID> ids);

    @Modifying
    @Query("UPDATE Post p SET p.deleted = CURRENT_TIMESTAMP WHERE p.id=:id AND p.deleted IS NULL")
    void delete(@Param("id")UUID id);

}
