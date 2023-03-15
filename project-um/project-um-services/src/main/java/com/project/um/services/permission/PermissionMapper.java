package com.project.um.services.permission;

import com.project.um.entities.Permission;
import com.project.um.request.PermissionRequest;
import com.project.um.response.PermissionResponse;
import com.project.um.services.mapper.Mapper;
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
