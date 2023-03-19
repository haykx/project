package com.project.publisher.services.publisher;

import com.project.publisher.entities.Publisher;
import com.project.publisher.request.PublisherRequest;
import com.project.publisher.response.PublisherResponse;
import com.project.publisher.services.discussion.DiscussionMapper;
import com.project.publisher.services.mapper.Mapper;
import com.project.publisher.services.token.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PublisherMapper implements Mapper<PublisherRequest, Publisher, PublisherResponse> {

    private final DiscussionMapper discussionMapper;
    private final AuthFacade facade;

    @Override
    public Publisher toEntity(final PublisherRequest request) {
        Publisher publisher = new Publisher();
        publisher.setFirstName(request.getFirstName());
        publisher.setLastName(request.getLastName());
        publisher.setBio(request.getBio());
        publisher.setOriginalId(facade.getOriginalId());
        return publisher;
    }


    @Override
    public PublisherResponse toResponse(final Publisher publisher) {
        PublisherResponse response = new PublisherResponse();
        response.setId(publisher.getId());
        response.setFirstName(publisher.getFirstName());
        response.setLastName(publisher.getLastName());
        response.setBio(publisher.getBio());
        response.setDiscussions(publisher.getDiscussions().stream().map(discussionMapper::toResponse).sorted((o1, o2) -> {
            if (o1.getCreated().isAfter(o2.getCreated())){
                return -1;
            } else if (o1.getCreated().isBefore(o2.getCreated())) {
                return 1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList()));
        response.setCreated(publisher.getCreated());
        response.setUpdated(publisher.getUpdated());
        return response;
    }
}
