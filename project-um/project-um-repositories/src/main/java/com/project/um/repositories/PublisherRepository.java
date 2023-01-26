package com.project.um.repositories;

import com.project.um.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID> {
    @Query("SELECT Publisher FROM Publisher p WHERE p.email = :email AND p.deleted IS NULL ")
    Optional<Publisher> findByEmailAndDeletedIsNull(@Param("email")String email);

    @Query("SELECT Publisher FROM Publisher p WHERE p.id = :id")
    Optional<Publisher> findByIdAndDeletedIsNull(@Param("id")UUID id);

    @Query("SELECT CASE WHEN COUNT(p)>0 THEN TRUE ELSE FALSE END FROM Publisher p WHERE p.email=email AND p.deleted IS NULL")
    boolean existsByEmailAndDeletedIsNull(@Param("email")String email);

    List<Publisher> findAllByIdInAndDeletedIsNull(List<UUID> id);

    @Query("UPDATE Publisher p SET p.deleted = CURRENT_TIMESTAMP WHERE p.id = :id AND p.deleted IS NULL")
    void delete(@Param("id")UUID id);
}
