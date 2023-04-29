package com.project.api.endpoint;

import com.project.entities.request.UmPublisherRequest;
import com.project.entities.request.UmPublisherUpdateDto;
import com.project.entities.response.UmPublisherResponse;
import com.project.services.publisher.UmPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/um/publisher")
public class UmPublisherEndpoint {
    private final UmPublisherService service;

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody UmPublisherRequest request) {
        return this.service.add(request);
    }

    @GetMapping("/{id}")
    public UmPublisherResponse get(@PathVariable("id") UUID id) {
        return this.service.get(id);
    }

    @GetMapping
    public List<UmPublisherResponse> get(@RequestParam("ids") List<UUID> ids) {
        return this.service.get(ids);
    }

    @PatchMapping
    public UmPublisherResponse update(@RequestParam("id") UUID id, @RequestBody UmPublisherUpdateDto dto) {
        return this.service.update(id, dto);
    }

    @DeleteMapping
    public void delete(@RequestParam("id") UUID id) {
        this.service.delete(id);
    }
}
