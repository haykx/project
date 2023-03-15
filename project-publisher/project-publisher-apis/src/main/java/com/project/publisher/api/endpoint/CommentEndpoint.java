package com.project.publisher.api.endpoint;

import com.project.publisher.request.CommentRequest;
import com.project.publisher.response.CommentResponse;
import com.project.publisher.services.comment.CommentLikeService;
import com.project.publisher.services.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentEndpoint {
    private final CommentService service;
    private final CommentLikeService likeService;

    @PostMapping
    public CommentResponse add(@RequestBody final CommentRequest request){
        return this.service.add(request);
    }

    @GetMapping("/{id}")
    public CommentResponse get(@PathVariable("id")final UUID id){
        return this.service.get(id);
    }

//    @GetMapping
//    public List<DiscussionResponse> search(final DiscussionQuery query){
//        return this.service.search(query);
//    }

//    @PatchMapping
//    public DiscussionResponse update(@RequestParam("id")final UUID id, @RequestBody DiscussionUpdateDto dto){
//        return this.service.update(id, dto);
//    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id")final UUID id){
        return this.service.delete(id);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<?> like(@PathVariable("id")final UUID id){
        return this.likeService.like(id);
    }

    @PostMapping("/{id}/unlike")
    public ResponseEntity<?> unlike(@PathVariable("id")final UUID id){
        return this.likeService.unlike(id);
    }
}
