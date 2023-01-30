package com.project.publisher.api.endpoint;

import com.project.publisher.services.post.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostLikeEndpoint {
    private final PostLikeService likeService;


    @PostMapping("/like/{id}")
    public ResponseEntity<?> like(@PathVariable("id") UUID id){
        return this.likeService.like(id);
    }

    @PostMapping("/unlike/{id}")
    public ResponseEntity<?> unlike(@PathVariable("id")UUID id){
        return this.likeService.unlike(id);
    }
}
