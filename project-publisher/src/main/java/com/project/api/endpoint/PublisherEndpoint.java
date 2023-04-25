package com.project.api.endpoint;

import com.project.entities.request.PublisherRequest;
import com.project.entities.request.PublisherUpdateDto;
import com.project.entities.response.PublisherResponse;
import com.project.services.publisher.PublisherService;
import com.project.services.query.PublisherQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/publisher/publisher")
public class PublisherEndpoint {

    private final PublisherService service;

    @PostMapping
    public PublisherResponse add(@RequestBody(required = false)final PublisherRequest request){
        return this.service.add(request);
    }

    @GetMapping("/{id}")
    public PublisherResponse get(@PathVariable("id")final UUID id){
        return this.service.get(id);
    }

    @GetMapping
    public List<PublisherResponse> search(final PublisherQuery query){
        return this.service.search(query);
    }

    @PatchMapping
    public PublisherResponse update(@RequestParam("id")final UUID id, @RequestBody PublisherUpdateDto dto){
        return this.service.update(id, dto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public PublisherResponse me() {
        return this.service.me();
    }

    @DeleteMapping
    public void delete(@RequestParam("id")final UUID id){
        this.service.delete(id);
    }
}
