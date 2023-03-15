package com.project.publisher.api.endpoint;

import com.project.publisher.request.DiscussionRequest;
import com.project.publisher.request.DiscussionUpdateDto;
import com.project.publisher.response.DiscussionResponse;
import com.project.publisher.services.discussion.DiscussionService;
import com.project.publisher.services.query.DiscussionQuery;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/discussion")
public class DiscussionEndpoint {
    private final DiscussionService service;

    @PostMapping
    public DiscussionResponse add(@RequestBody final DiscussionRequest request){
        return this.service.add(request);
    }

    @GetMapping("/{id}")
    public DiscussionResponse get(@PathVariable("id")final UUID id){
        return this.service.get(id);
    }

    @GetMapping
    public List<DiscussionResponse> search(final DiscussionQuery query){
        return this.service.search(query);
    }

    @PatchMapping
    public DiscussionResponse update(@RequestParam("id")final UUID id, @RequestBody DiscussionUpdateDto dto){
        return this.service.update(id, dto);
    }

    @DeleteMapping
    public void delete(@RequestParam("id")final UUID id){
        this.service.delete(id);
    }
}