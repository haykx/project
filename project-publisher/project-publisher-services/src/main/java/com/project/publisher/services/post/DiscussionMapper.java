package com.project.publisher.services.post;

import com.project.publisher.entities.Discussion;
import com.project.publisher.entities.Publisher;
import com.project.publisher.repositories.PublisherRepository;
import com.project.publisher.request.DiscussionRequest;
import com.project.publisher.response.DiscussionResponse;
import com.project.publisher.services.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@RequiredArgsConstructor
public class DiscussionMapper implements Mapper<DiscussionRequest, Discussion, DiscussionResponse> {

    private final PublisherRepository publisherRepository;

    @Override
    public Discussion toEntity(final DiscussionRequest request) {
        Discussion discussion = new Discussion();
        discussion.setPublisher(this.getPublisher(request));
        discussion.setQuestion(request.getQuestion());
        discussion.setImage(Base64.getDecoder().decode(request.getImage().substring(23)));
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
        if (discussion.getImage() != null) {
            response.setImage(new String(Base64.getEncoder().encode(discussion.getImage()), UTF_8));
        }
        response.setCreated(discussion.getCreated());
        response.setLikes(discussion.getLikes());
        response.setUpdated(discussion.getUpdated());
        response.setDeleted(discussion.getDeleted());
        return response;
    }
}
