package com.project.publisher.api.endpoint;

import com.project.publisher.services.discussion.DiscussionLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/discussion")
public class DiscussionLikeEndpoint {
    private final DiscussionLikeService likeService;


    @PostMapping("/{id}/like")
    public ResponseEntity<?> like(@PathVariable("id")final UUID id){
        return this.likeService.like(id);
    }

    @PostMapping("/{id}/unlike")
    public ResponseEntity<?> unlike(@PathVariable("id")final UUID id){
        return this.likeService.unlike(id);
    }
}
