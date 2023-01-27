package com.project.publisher.services.publisher;

import com.project.publisher.entities.Publisher;
import com.project.publisher.repositories.PublisherRepository;
import com.project.publisher.request.PublisherRequest;
import com.project.publisher.request.PublisherUpdateDto;
import com.project.publisher.response.PublisherResponse;
import com.project.publisher.services.query.PublisherQuery;
import com.project.publisher.services.specification.PublisherSpecificationBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherService implements UserService {

    private final PublisherMapper mapper;
    private final PublisherSpecificationBuilder specificationBuilder;
    private final PublisherRepository repository;

    @Override
    public PublisherResponse add(PublisherRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    @Override
    public PublisherResponse get(UUID id) {
        return this.mapper.toResponse(this.repository.findByIdAndDeletedIsNull(id).orElseThrow());
    }

    @Override
    @Transactional
    public PublisherResponse update(UUID id, PublisherUpdateDto dto) {
        Publisher publisher = this.repository.findByIdAndDeletedIsNull(id).orElseThrow();
        Optional.ofNullable(dto.getFirstName()).ifPresent(publisher::setFirstName);
        Optional.ofNullable(dto.getLastName()).ifPresent(publisher::setLastName);
        Optional.ofNullable(dto.getAvatar()).ifPresent(publisher::setAvatar);
        Optional.ofNullable(dto.getBio()).ifPresent(publisher::setBio);
        return this.mapper.toResponse(this.repository.save(publisher));
    }

    @Override
    @Transactional
    public List<PublisherResponse> search(PublisherQuery query) {
        return this.repository.findAll(
                specificationBuilder.searchPublishers(query),
                query.getPageable()
        ).getContent().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        this.repository.delete(id);
    }
}
