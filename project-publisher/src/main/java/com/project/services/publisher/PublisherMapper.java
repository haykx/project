package com.project.services.publisher;

import com.project.entities.model.Publisher;
import com.project.entities.request.PublisherRequest;
import com.project.entities.response.PublisherResponse;
import com.project.services.discussion.DiscussionMapper;
import com.project.services.mapper.Mapper;
import com.project.services.token.AuthFacade;
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
        publisher.setDateOfBirth(request.getDateOfBirth());
        publisher.setOriginalId(facade.getOriginalId());
        return publisher;
    }


    @Override
    public PublisherResponse toResponse(final Publisher publisher) {
        PublisherResponse response = new PublisherResponse();
        response.setId(publisher.getId());
        response.setFirstName(publisher.getFirstName());
        response.setLastName(publisher.getLastName());
        response.setDateOfBirth(publisher.getDateOfBirth());
        response.setDiscussions(publisher.getDiscussions().stream().map(discussionMapper::toResponse).sorted((o1, o2) -> {
            if (o1.getCreated().isAfter(o2.getCreated())) {
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
