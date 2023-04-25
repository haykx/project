package com.project.services.discussion;

import com.project.entities.model.Discussion;
import com.project.entities.model.Publisher;
import com.project.repositories.PublisherRepository;
import com.project.entities.request.DiscussionRequest;
import com.project.entities.response.DiscussionResponse;
import com.project.services.comment.CommentMapper;
import com.project.services.mapper.Mapper;
import com.project.services.token.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DiscussionMapper implements Mapper<DiscussionRequest, Discussion, DiscussionResponse> {

    private final PublisherRepository publisherRepository;
    private final AuthFacade facade;
    private final CommentMapper commentMapper;

    @Override
    public Discussion toEntity(final DiscussionRequest request) {
        Discussion discussion = new Discussion();
        discussion.setPublisher(this.getPublisher(request));
        discussion.setQuestion(request.getQuestion());
        discussion.setBody(request.getBody());
        discussion.setLikes(0);
        return discussion;
    }

    private Publisher getPublisher(final DiscussionRequest request) {
        return this.publisherRepository.findById(request.getPublisherId()).orElseThrow();
    }

    @Override
    public DiscussionResponse toResponse(final Discussion discussion) {
        DiscussionResponse response = new DiscussionResponse();
        response.setComments(discussion.getComments().stream().map(commentMapper::toResponse).sorted((o1, o2) -> {
            if (o1.getCreated().isAfter(o2.getCreated())){
                return -1;
            } else if (o1.getCreated().isBefore(o2.getCreated())) {
                return 1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList()));
        response.setId(discussion.getId());
        response.setQuestion(discussion.getQuestion());
        response.setPublisherId(discussion.getPublisher().getId());
        response.setPublisherName(discussion.getPublisher().getFullName());
        response.setBody(discussion.getBody());
        response.setCreated(discussion.getCreated());
        response.setLikes(discussion.getLikes());
        try {
            final UUID pub = this.publisherRepository.getIdByOriginalId(facade.getOriginalId());
            response.setLiked(discussion.getLikers().contains(pub));
        } catch (ClassCastException e) {
            response.setLiked(false);
        }
        response.setUpdated(discussion.getUpdated());
        return response;
    }
}
