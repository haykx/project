package com.project.um.repositories;

import com.project.um.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Override
    Optional<Role> findById(UUID id);

    List<Role> findAllByIdIn(List<UUID> ids);
    @Query("SELECT r FROM Role r")
    List<Role> getAll();

    default List<Role> getDefaultRoles(){
        return getAll();
    }
}
