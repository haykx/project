package com.project.publisher.services.comment;

import com.project.publisher.entities.Comment;
import com.project.publisher.repositories.CommentRepository;
import com.project.publisher.request.CommentRequest;
import com.project.publisher.response.CommentResponse;
import com.project.publisher.services.exceptions.BadRequestException;
import com.project.publisher.services.exceptions.NotFoundException;
import com.project.publisher.services.token.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;
    private final CommentMapper mapper;
    private final AuthFacade facade;

    public CommentResponse add(final CommentRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    public CommentResponse get(final UUID id) {
        return this.mapper.toResponse(this.repository.findById(id).orElseThrow(() -> new NotFoundException(id)));
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

}
