package com.project.publisher.api.endpoint;

import com.project.publisher.request.PublisherRequest;
import com.project.publisher.request.PublisherUpdateDto;
import com.project.publisher.response.PublisherResponse;
import com.project.publisher.services.publisher.PublisherService;
import com.project.publisher.services.query.PublisherQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/publisher")
public class PublisherEndpoint {

    private final PublisherService service;

    @PostMapping
    public PublisherResponse add(@RequestBody PublisherRequest request){
        return this.service.add(request);
    }

    @GetMapping("/{id}")
    public PublisherResponse get(@PathVariable("id")UUID id){
        return this.service.get(id);
    }

    @GetMapping
    public List<PublisherResponse> search(PublisherQuery query){
        return this.service.search(query);
    }

    @PatchMapping
    public PublisherResponse update(@RequestParam("id")UUID id, @RequestBody PublisherUpdateDto dto){
        return this.service.update(id, dto);
    }

    @DeleteMapping
    public void delete(@RequestParam("id")UUID id){
        this.service.delete(id);
    }
}
