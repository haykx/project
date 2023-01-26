package com.project.um.repositories;

import com.project.um.entities.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, UUID> {
    void deleteAllByPk_PermissionId(UUID id);

    void deleteAllByPk_RoleId(UUID id);
}
