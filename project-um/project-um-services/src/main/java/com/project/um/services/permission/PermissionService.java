package com.project.um.services.permission;

import com.project.um.repositories.PermissionRepository;
import com.project.um.request.PermissionRequest;
import com.project.um.response.PermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionService implements UserPermissionService<PermissionRequest, PermissionResponse> {

    private final PermissionRepository repository;

    @Override
    public PermissionResponse add(PermissionRequest request) {
        return null;
    }

    @Override
    public PermissionResponse get(UUID id) {
        return null;
    }

    @Override
    public List<PermissionResponse> get(List<UUID> ids) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
