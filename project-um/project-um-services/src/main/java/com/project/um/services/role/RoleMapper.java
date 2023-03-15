package com.project.um.services.role;

import com.project.um.entities.Permission;
import com.project.um.entities.Role;
import com.project.um.repositories.PermissionRepository;
import com.project.um.request.RoleRequest;
import com.project.um.response.RoleResponse;
import com.project.um.services.mapper.Mapper;
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
