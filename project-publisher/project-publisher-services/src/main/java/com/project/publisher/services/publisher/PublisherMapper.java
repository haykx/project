package com.project.publisher.services.publisher;

import com.project.publisher.entities.Publisher;
import com.project.publisher.reg.PublisherPrincipal;
import com.project.publisher.request.PublisherRequest;
import com.project.publisher.response.PublisherResponse;
import com.project.publisher.services.mapper.Mapper;
import com.project.publisher.services.post.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PublisherMapper implements Mapper<PublisherRequest, Publisher, PublisherResponse> {

    private final PostMapper postMapper;

    @Override
    public Publisher toEntity(final PublisherRequest request) {
        Publisher publisher = new Publisher();
        publisher.setFirstName(request.getFirstName());
        publisher.setLastName(request.getLastName());
        publisher.setBio(request.getBio());
        publisher.setAvatar(request.getAvatar());
        publisher.setOriginalId(this.getIdFromToken());
        return publisher;
    }


    @Override
    public PublisherResponse toResponse(final Publisher publisher) {
        PublisherResponse response = new PublisherResponse();
        response.setId(publisher.getId());
        response.setFirstName(publisher.getFirstName());
        response.setLastName(publisher.getLastName());
        response.setBio(publisher.getBio());
        response.setAvatar(publisher.getAvatar());
        response.setPosts(publisher.getPosts().stream().map(postMapper::toResponse).collect(Collectors.toList()));
        response.setCreated(publisher.getCreated());
        response.setUpdated(publisher.getUpdated());
        response.setDeleted(publisher.getDeleted());
        return response;
    }

    private UUID getIdFromToken() {
        return ((PublisherPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
