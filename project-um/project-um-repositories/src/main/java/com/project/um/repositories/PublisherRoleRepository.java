package com.project.um.repositories;

import com.project.um.entities.Permission;
import com.project.um.entities.PublisherRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublisherRoleRepository extends JpaRepository<PublisherRole, UUID> {
}
