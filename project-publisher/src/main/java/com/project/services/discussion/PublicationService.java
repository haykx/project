package com.project.services.discussion;

import com.project.entities.request.DiscussionRequest;
import com.project.entities.request.DiscussionUpdateDto;
import com.project.entities.response.DiscussionResponse;
import com.project.services.query.DiscussionQuery;

import java.util.List;
import java.util.UUID;

public interface PublicationService {
    DiscussionResponse add(DiscussionRequest request);
    DiscussionResponse get(UUID id);
    List<DiscussionResponse> search(DiscussionQuery query);
    DiscussionResponse update(UUID id, DiscussionUpdateDto dto);
    void delete(UUID id);
}
