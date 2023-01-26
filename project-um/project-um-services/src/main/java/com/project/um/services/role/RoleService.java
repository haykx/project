package com.project.um.services.role;

import com.project.um.repositories.PublisherRoleRepository;
import com.project.um.repositories.RolePermissionRepository;
import com.project.um.repositories.RoleRepository;
import com.project.um.request.RoleRequest;
import com.project.um.response.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService implements UserRoleService {

    private final RoleMapper mapper;
    private final RoleRepository repository;
    private final RolePermissionRepository permissionCrossRepository;
    private final PublisherRoleRepository publisherCrossRepository;

    @Override
    public RoleResponse add(RoleRequest request) {
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    public RoleResponse get(UUID id) {
        return this.mapper.toResponse(this.repository.findByIdAndDeletedIsNull(id).orElseThrow());
    }

    @Override
    public List<RoleResponse> get(List<UUID> ids) {
        return this.repository.findAllByIdInAndDeletedIsNull(ids).stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        this.publisherCrossRepository.deleteAllByPk_RoleId(id);
        this.permissionCrossRepository.deleteAllByPk_RoleId(id);
        this.repository.delete(id);
    }
}
