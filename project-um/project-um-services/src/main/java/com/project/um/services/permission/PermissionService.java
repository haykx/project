package com.project.um.services.permission;

import com.project.um.repositories.PermissionRepository;
import com.project.um.repositories.RolePermissionRepository;
import com.project.um.request.PermissionRequest;
import com.project.um.response.PermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionService implements UserPermissionService {

    private final PermissionRepository repository;
    private final RolePermissionRepository crossRepository;
    private final PermissionMapper mapper;

    @Override
    public PermissionResponse add(PermissionRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    @Override
    public PermissionResponse get(UUID id) {
        return this.mapper.toResponse(this.repository.findByIdAndDeletedIsNull(id).orElseThrow());
    }

    @Override
    public List<PermissionResponse> get(List<UUID> ids) {
        return this.repository.findAllByIdInAndDeletedIsNull(ids).stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        this.crossRepository.deleteAllByPk_PermissionId(id);
        this.repository.delete(id);
    }
}
