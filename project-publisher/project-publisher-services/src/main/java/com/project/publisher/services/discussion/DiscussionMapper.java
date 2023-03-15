package com.project.publisher.services.discussion;

import com.project.publisher.entities.Discussion;
import com.project.publisher.entities.Publisher;
import com.project.publisher.repositories.PublisherRepository;
import com.project.publisher.request.DiscussionRequest;
import com.project.publisher.response.DiscussionResponse;
import com.project.publisher.services.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscussionMapper implements Mapper<DiscussionRequest, Discussion, DiscussionResponse> {

    private final PublisherRepository publisherRepository;

    @Override
    public Discussion toEntity(final DiscussionRequest request) {
        Discussion discussion = new Discussion();
        discussion.setPublisher(this.getPublisher(request));
        discussion.setQuestion(request.getQuestion());
        discussion.setBody(request.getBody());
        discussion.setLink(request.getLink());
        discussion.setLikes(0);
        return discussion;
    }

    private Publisher getPublisher(final DiscussionRequest request) {
        return this.publisherRepository.findById(request.getPublisherId()).orElseThrow();
    }

    @Override
    public DiscussionResponse toResponse(final Discussion discussion) {
        DiscussionResponse response = new DiscussionResponse();
        response.setId(discussion.getId());
        response.setQuestion(discussion.getQuestion());
        response.setPublisherId(discussion.getPublisher().getId());
        response.setLink(discussion.getLink());
        response.setBody(discussion.getBody());
        response.setCreated(discussion.getCreated());
        response.setLikes(discussion.getLikes());
        response.setUpdated(discussion.getUpdated());
        return response;
    }
}
