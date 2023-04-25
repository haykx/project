package com.project.um.services.role;

import com.project.um.entities.request.RoleRequest;
import com.project.um.entities.response.RoleResponse;
import com.project.um.repositories.RoleRepository;
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

    @Override
    public RoleResponse add(RoleRequest request) {
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    public RoleResponse get(UUID id) {
        return this.mapper.toResponse(this.repository.findById(id).orElseThrow());
    }

    @Override
    public List<RoleResponse> get(List<UUID> ids) {
        return this.repository.findAllByIdIn(ids).stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        this.repository.deleteById(id);
    }
}
