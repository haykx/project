package com.project.services.comment;

import com.project.entities.model.Comment;
import com.project.repositories.CommentRepository;
import com.project.repositories.PublisherRepository;
import com.project.entities.request.CommentRequest;
import com.project.entities.response.CommentResponse;
import com.project.services.exceptions.BadRequestException;
import com.project.services.exceptions.NotFoundException;
import com.project.services.token.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;
    private final PublisherRepository publisherRepository;
    private final CommentMapper mapper;
    private final AuthFacade facade;

    public CommentResponse add(final CommentRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    public CommentResponse get(final UUID id) {
        return this.mapper.toGetResponse(this.repository.findById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    @Transactional
    public ResponseEntity<?> delete(final UUID id) {
        Comment comment = this.repository.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.validate(comment);
        this.repository.delete(comment);
        return ResponseEntity.ok().build();
    }

    private void validate(Comment comment) {
        if (!comment.getPublisher().getOriginalId().equals(facade.getOriginalId())) {
            throw new BadRequestException("");
        }
    }

    @Transactional
    public ResponseEntity<?> like(final UUID id){
        Comment comment = this.repository.findById(id).orElseThrow(() -> new NotFoundException(id));
        UUID pubId = publisherRepository.getIdByOriginalId(facade.getOriginalId());
        comment.like(pubId);
        this.repository.save(comment);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<?> unlike(final UUID id){
        Comment comment = this.repository.findById(id).orElseThrow(() -> new NotFoundException(id));
        UUID pubId = publisherRepository.getIdByOriginalId(facade.getOriginalId());
        comment.unlike(pubId);
        this.repository.save(comment);
        return ResponseEntity.ok().build();
    }

}
