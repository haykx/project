package com.project.api.endpoint;

import com.project.entities.request.RoleRequest;
import com.project.entities.response.RoleResponse;
import com.project.services.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/um/role")
public class RoleEndpoint {
    private final RoleService service;

    @PostMapping
    public RoleResponse add(@RequestBody RoleRequest request) {
        return this.service.add(request);
    }

    @GetMapping("/{id}")
    public RoleResponse get(@PathVariable("id") UUID id) {
        return this.service.get(id);
    }

    @GetMapping
    public List<RoleResponse> get(@RequestParam("ids") List<UUID> ids) {
        return this.service.get(ids);
    }

    @DeleteMapping
    public void delete(@RequestParam("id") UUID id) {
        this.service.delete(id);
    }

}
