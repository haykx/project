package com.project.publisher.services.publisher;

import com.project.publisher.request.PublisherRequest;
import com.project.publisher.request.PublisherUpdateDto;
import com.project.publisher.response.PublisherResponse;
import com.project.publisher.services.query.PublisherQuery;

import java.util.List;
import java.util.UUID;

public interface UserService{
    PublisherResponse add(PublisherRequest request);

    PublisherResponse get(UUID id);
    PublisherResponse update(UUID id, PublisherUpdateDto dto);
    List<PublisherResponse> search(PublisherQuery query);

    void delete(UUID id);

}
