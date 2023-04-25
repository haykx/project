package com.project.services.publisher;

import com.project.entities.request.PublisherRequest;
import com.project.entities.request.PublisherUpdateDto;
import com.project.entities.response.PublisherResponse;
import com.project.services.query.PublisherQuery;

import java.util.List;
import java.util.UUID;

public interface UserService{
    PublisherResponse add(PublisherRequest request);

    PublisherResponse get(UUID id);
    PublisherResponse update(UUID id, PublisherUpdateDto dto);
    List<PublisherResponse> search(PublisherQuery query);

    void delete(UUID id);

}
