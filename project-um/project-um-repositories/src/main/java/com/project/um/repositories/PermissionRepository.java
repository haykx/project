package com.project.um.repositories;

import com.project.um.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    @Override
    @Query("SELECT Permission FROM Permission p WHERE p.id = :id")
    Optional<Permission> findById(@Param("id")UUID id);

    List<Permission> findAllByIdIn(List<UUID> ids);
}
