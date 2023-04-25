package com.project.services.permission;

import com.project.entities.model.Permission;
import com.project.entities.request.PermissionRequest;
import com.project.entities.response.PermissionResponse;
import com.project.services.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper implements Mapper<PermissionRequest, Permission, PermissionResponse> {

    @Override
    public Permission toEntity(PermissionRequest request) {
        return new Permission(request.getName());
    }

    @Override
    public PermissionResponse toResponse(Permission permission) {
        PermissionResponse response = new PermissionResponse();
        response.setId(permission.getId());
        response.setName(permission.getName());
        response.setCreated(permission.getCreated());
        response.setUpdated(permission.getUpdated());
        return response;
    }
}
