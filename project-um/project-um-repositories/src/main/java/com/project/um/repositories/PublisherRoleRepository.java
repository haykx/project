package com.project.um.repositories;

import com.project.um.entities.PublisherRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublisherRoleRepository extends JpaRepository<PublisherRole, UUID> {
    @Modifying
    @Query("DELETE FROM PublisherRole p WHERE p.pk.publisherId = :id")
    void deleteAllByPk_PublisherId(@Param("id") UUID id);

    @Modifying
    @Query("DELETE FROM PublisherRole p WHERE p.pk.roleId = :id")
    void deleteAllByPk_RoleId(@Param("id") UUID id);
}
