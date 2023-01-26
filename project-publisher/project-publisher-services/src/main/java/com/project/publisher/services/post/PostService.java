package com.project.publisher.services.post;

import com.project.publisher.repositories.PostRepository;
import com.project.publisher.request.PostRequest;
import com.project.publisher.request.PostUpdateDto;
import com.project.publisher.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService implements PublicationService {

    private final PostRepository repository;
    private final PostMapper mapper;

    @Override
    public PostResponse add(PostRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    @Override
    public PostResponse get(UUID id) {
        return this.mapper.toResponse(this.repository.findByIdAndDeletedIsNull(id).orElseThrow());
    }

    @Override
    public List<PostResponse> get(List<UUID> ids) {
        return this.repository.findAllByIdInAndDeletedIsNull(ids).stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<PostResponse> getPopularPosts() {
        return null;
    }

    @Override
    public PostResponse update(PostUpdateDto dto) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
