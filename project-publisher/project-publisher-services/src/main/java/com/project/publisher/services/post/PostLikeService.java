package com.project.publisher.services.post;

import com.project.publisher.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeService {

    private final PostRepository repository;

    public ResponseEntity<?> like(final UUID id){
        try{
           this.repository.like(id);
           return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> unlike(final UUID id){
        try{
            this.repository.unlike(id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
