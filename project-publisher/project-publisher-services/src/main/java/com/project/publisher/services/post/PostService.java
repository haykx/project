package com.project.publisher.services.post;

import com.project.publisher.entities.Post;
import com.project.publisher.repositories.PostRepository;
import com.project.publisher.request.PostRequest;
import com.project.publisher.request.PostUpdateDto;
import com.project.publisher.response.PostResponse;
import com.project.publisher.services.query.PostQuery;
import com.project.publisher.services.specification.PostSpecificationBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService implements PublicationService {

    private final PostRepository repository;
    private final PostSpecificationBuilder specificationBuilder;
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
    @Transactional
    public List<PostResponse> search(PostQuery query) {
        return this.repository.findAll(
                specificationBuilder.searchPosts(query),
                query.getPageable()
        ).getContent().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PostResponse update(UUID id, PostUpdateDto dto) {
        Post post = this.repository.findByIdAndDeletedIsNull(id).orElseThrow();
        Optional.ofNullable(dto.getHeadline()).ifPresent(post::setHeadline);
        Optional.ofNullable(dto.getHeadline()).ifPresent(post::setHeadline);
        Optional.ofNullable(dto.getBody()).ifPresent(post::setBody);
        Optional.ofNullable(dto.getLink()).ifPresent(post::setLink);
        return this.mapper.toResponse(this.repository.save(post));
    }

    @Override
    public void delete(UUID id) {
        this.repository.delete(id);
    }
}
