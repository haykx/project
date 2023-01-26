package com.project.um.repositories;

import com.project.um.entities.UmPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<UmPublisher, UUID> {
    @Query("SELECT UmPublisher FROM UmPublisher p WHERE p.email = :email AND p.deleted IS NULL ")
    Optional<UmPublisher> findByEmailAndDeletedIsNull(@Param("email")String email);

    @Query("SELECT UmPublisher FROM UmPublisher p WHERE p.id = :id")
    Optional<UmPublisher> findByIdAndDeletedIsNull(@Param("id")UUID id);

    @Query("SELECT CASE WHEN COUNT(p)>0 THEN TRUE ELSE FALSE END FROM UmPublisher p WHERE p.email=:email AND p.deleted IS NULL")
    boolean existsByEmailAndDeletedIsNull(@Param("email")String email);

    List<UmPublisher> findAllByIdInAndDeletedIsNull(List<UUID> id);

    @Modifying
    @Query("UPDATE UmPublisher p SET p.deleted = CURRENT_TIMESTAMP, p.updated = CURRENT_TIMESTAMP WHERE p.id = :id AND p.deleted IS NULL")
    void delete(@Param("id")UUID id);
}
