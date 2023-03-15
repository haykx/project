package com.project.publisher.services.comment;

import com.project.publisher.entities.Comment;
import com.project.publisher.repositories.CommentRepository;
import com.project.publisher.repositories.DiscussionRepository;
import com.project.publisher.repositories.PublisherRepository;
import com.project.publisher.request.CommentRequest;
import com.project.publisher.response.CommentResponse;
import com.project.publisher.services.exceptions.BadRequestException;
import com.project.publisher.services.exceptions.NotFoundException;
import com.project.publisher.services.mapper.Mapper;
import com.project.publisher.services.token.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentMapper implements Mapper<CommentRequest, Comment, CommentResponse> {

    private final DiscussionRepository discussionRepository;
    private final PublisherRepository publisherRepository;
    private final CommentRepository repository;
    private final AuthFacade facade;

    @Override
    public Comment toEntity(CommentRequest request) {
        Comment comment = new Comment();
        UUID publisherId = facade.getOriginalId();
        comment.setPublisher(this.publisherRepository.findByOriginalId(publisherId).orElseThrow(()->new NotFoundException(publisherId)));
        if(request.getDiscussionId()!=null){
            comment.setDiscussion(this.discussionRepository.findById(request.getDiscussionId()).orElseThrow(()->new NotFoundException(request.getDiscussionId())));
        } else if (request.getCommentId() != null) {
            comment.setParent(this.repository.findById(request.getCommentId()).orElseThrow(() -> new NotFoundException(request.getCommentId())));
        } else {
            throw new BadRequestException("");
        }
        comment.setLikes(0);
        comment.setText(request.getText());
        return comment;
    }

    @Override
    public CommentResponse toResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setText(comment.getText());
        response.setLikes(comment.getLikes());
        response.setDiscussionId(comment.getDiscussion().getId());
        response.setParentId(comment.getParent().getId());
        response.setCreated(comment.getCreated());
        response.setUpdated(comment.getUpdated());
        response.setReplies(comment.getReplies().stream().map(this::toResponse).collect(Collectors.toList()));
        return response;
    }
}
