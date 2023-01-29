package com.project.publisher.api.endpoint;

import com.project.publisher.request.PostRequest;
import com.project.publisher.request.PostUpdateDto;
import com.project.publisher.response.PostResponse;
import com.project.publisher.services.post.PostService;
import com.project.publisher.services.query.PostQuery;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/post")
public class PostEndpoint {
    private final PostService service;

    @PostMapping
    public PostResponse add(@RequestBody PostRequest request){
        return this.service.add(request);
    }

    @GetMapping("/{id}")
    public PostResponse get(@PathVariable UUID id){
        return this.service.get(id);
    }

    @GetMapping
    public List<PostResponse> search(PostQuery query){
        return this.service.search(query);
    }

    @PatchMapping
    public PostResponse update(@RequestParam("id")UUID id, @RequestBody PostUpdateDto dto){
        return this.service.update(id, dto);
    }

    @DeleteMapping
    public void delete(@RequestParam("id")UUID id){
        this.service.delete(id);
    }
}
