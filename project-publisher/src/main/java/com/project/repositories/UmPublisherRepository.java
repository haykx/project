package com.project.repositories;

import com.project.entities.model.UmPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UmPublisherRepository extends JpaRepository<UmPublisher, UUID> {
    //    @Query("SELECT UmPublisher FROM UmPublisher p WHERE p.email = :email AND p.deleted IS NULL ")
    Optional<UmPublisher> findByEmail(String email);

    @Override
    @Query("SELECT UmPublisher FROM UmPublisher p WHERE p.id = :id")
    Optional<UmPublisher> findById(@Param("id") UUID id);

    @Query("SELECT CASE WHEN COUNT(p)>0 THEN TRUE ELSE FALSE END FROM UmPublisher p WHERE p.email=:email")
    boolean existsByEmail(@Param("email") String email);

    List<UmPublisher> findAllByIdIn(List<UUID> id);

}
