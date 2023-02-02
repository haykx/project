package com.project.um.api.endpoints;

import com.project.um.request.PublisherRequest;
import com.project.um.request.PublisherUpdateDto;
import com.project.um.response.PublisherResponse;
import com.project.um.services.publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher")
public class PublisherEndpoint {
    private final PublisherService service;

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody PublisherRequest request){
        return this.service.add(request);
    }

    @GetMapping("/{id}")
    public PublisherResponse get(@PathVariable("id")UUID id){
        return this.service.get(id);
    }

    @GetMapping
    public List<PublisherResponse> get(@RequestParam("ids")List<UUID> ids){
        return this.service.get(ids);
    }

    @PatchMapping
    public PublisherResponse update(@RequestParam("id")UUID id, @RequestBody PublisherUpdateDto dto){
        return this.service.update(id ,dto);
    }

    @DeleteMapping
    public void delete(@RequestParam("id")UUID id){
        this.service.delete(id);
    }
}
