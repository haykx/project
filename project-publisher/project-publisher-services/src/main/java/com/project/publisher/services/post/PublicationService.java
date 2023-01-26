package com.project.publisher.services.post;

import com.project.publisher.request.PostRequest;
import com.project.publisher.request.PostUpdateDto;
import com.project.publisher.response.PostResponse;

import java.util.List;
import java.util.UUID;

public interface PublicationService {
    PostResponse add(PostRequest request);
    PostResponse get(UUID id);
    List<PostResponse> get(List<UUID> ids);
    List<PostResponse> getPopularPosts();
    PostResponse update(PostUpdateDto dto);
    void delete(UUID id);
}
