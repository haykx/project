package com.project.um.repositories;

import com.project.um.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    @Query("SELECT Permission FROM Permission p WHERE p.id = :id AND p.deleted IS NULL")
    Optional<Permission> findByIdAndDeletedIsNull(@Param("id")UUID id);

    List<Permission> findAllByIdInAndDeletedIsNull(List<UUID> ids);

    @Modifying
    @Query("UPDATE Permission p SET p.deleted = CURRENT_TIMESTAMP, p.updated = CURRENT_TIMESTAMP WHERE p.id = :id AND p.deleted IS NULL")
    void delete(UUID id);
}
