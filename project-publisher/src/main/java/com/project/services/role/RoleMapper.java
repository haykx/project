package com.project.services.role;

import com.project.entities.model.Permission;
import com.project.entities.model.Role;
import com.project.entities.request.RoleRequest;
import com.project.entities.response.RoleResponse;
import com.project.repositories.PermissionRepository;
import com.project.services.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleMapper implements Mapper<RoleRequest, Role, RoleResponse> {

    private final PermissionRepository permissionRepository;

    @Override
    public Role toEntity(RoleRequest request) {
        return new Role(request.getName(), this.getPermissions(request));
    }
    @Override
    public RoleResponse toResponse(Role role) {
        RoleResponse response = new RoleResponse();
        response.setId(role.getId());
        response.setName(role.getName());
        response.setCreated(role.getCreated());
        response.setUpdated(role.getUpdated());
        return response;
    }
    private List<Permission> getPermissions(RoleRequest request) {
        return this.permissionRepository.findAllByIdIn(request.getPermissions());
    }
}
