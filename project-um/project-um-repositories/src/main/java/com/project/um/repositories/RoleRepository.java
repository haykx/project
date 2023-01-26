package com.project.um.repositories;

import com.project.um.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByIdAndDeletedIsNull(UUID id);

    List<Role> findAllByIdInAndDeletedIsNull(List<UUID> ids);

    @Modifying
    @Query("UPDATE Role r SET r.deleted = CURRENT_TIMESTAMP, r.updated = CURRENT_TIMESTAMP WHERE r.id = :id AND r.deleted IS NULL")
    void delete(@Param("id")UUID id);
}
