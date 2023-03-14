package com.project.publisher.services.post;

import com.project.publisher.entities.Discussion;
import com.project.publisher.repositories.DiscussionRepository;
import com.project.publisher.request.DiscussionRequest;
import com.project.publisher.request.DiscussionUpdateDto;
import com.project.publisher.response.DiscussionResponse;
import com.project.publisher.services.exceptions.NotFoundException;
import com.project.publisher.services.query.DiscussionQuery;
import com.project.publisher.services.specification.DiscussionSpecificationBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscussionService implements PublicationService {

    private final DiscussionRepository repository;
    private final DiscussionSpecificationBuilder specificationBuilder;
    private final DiscussionMapper mapper;

    @Override
    public DiscussionResponse add(final DiscussionRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    @Override
    public DiscussionResponse get(final UUID id) {
        return this.mapper.toResponse(this.repository.findById(id).orElseThrow(()->new NotFoundException(id)));
    }


    @Override
    @Transactional
    public List<DiscussionResponse> search(final DiscussionQuery query) {
        return this.repository.findAll(
                specificationBuilder.searchPosts(query),
                query.getPageable()
        ).getContent().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DiscussionResponse update(final UUID id, final DiscussionUpdateDto dto) {
        Discussion discussion = this.repository.findById(id).orElseThrow();
        Optional.ofNullable(dto.getHeadline()).ifPresent(discussion::setQuestion);
        Optional.ofNullable(dto.getHeadline()).ifPresent(discussion::setQuestion);
        Optional.ofNullable(dto.getBody()).ifPresent(discussion::setBody);
        Optional.ofNullable(dto.getLink()).ifPresent(discussion::setLink);
        return this.mapper.toResponse(this.repository.save(discussion));
    }

    @Override
    public void delete(final UUID id) {
        this.repository.deleteById(id);
    }
}
