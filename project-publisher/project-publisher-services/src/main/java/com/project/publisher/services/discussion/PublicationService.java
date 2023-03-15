package com.project.publisher.services.discussion;

import com.project.publisher.request.DiscussionRequest;
import com.project.publisher.request.DiscussionUpdateDto;
import com.project.publisher.response.DiscussionResponse;
import com.project.publisher.services.query.DiscussionQuery;

import java.util.List;
import java.util.UUID;

public interface PublicationService {
    DiscussionResponse add(DiscussionRequest request);
    DiscussionResponse get(UUID id);
    List<DiscussionResponse> search(DiscussionQuery query);
    DiscussionResponse update(UUID id, DiscussionUpdateDto dto);
    void delete(UUID id);
}
