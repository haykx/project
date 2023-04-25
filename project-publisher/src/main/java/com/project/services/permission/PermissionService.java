package com.project.services.permission;

import com.project.entities.request.PermissionRequest;
import com.project.entities.response.PermissionResponse;
import com.project.repositories.PermissionRepository;
import com.project.services.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionService implements UserPermissionService {

    private final PermissionRepository repository;
    private final PermissionMapper mapper;

    @Override
    public PermissionResponse add(PermissionRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    @Override
    public PermissionResponse get(UUID id) {
        return this.mapper.toResponse(this.repository.findById(id).orElseThrow(()->new NotFoundException(id)));
    }

    @Override
    public List<PermissionResponse> get(List<UUID> ids) {
        return this.repository.findAllByIdIn(ids).stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        this.repository.deleteById(id);
    }
}
