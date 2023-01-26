package com.project.um.api.endpoints;

import com.project.um.request.PermissionRequest;
import com.project.um.response.PermissionResponse;
import com.project.um.services.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/permission")
public class PermissionEndpoint {
    private final PermissionService service;

    @PostMapping
    public PermissionResponse add(PermissionRequest request){
        return this.service.add(request);
    }

    @GetMapping("/{id}")
    public PermissionResponse get(@PathVariable("id")UUID id){
        return this.service.get(id);
    }

    @GetMapping
    public List<PermissionResponse> get(@RequestParam("ids") List<UUID> id){
        return this.service.get(id);
    }

    @DeleteMapping
    public void delete(@RequestParam("id")UUID id){
        this.service.delete(id);
    }
}
