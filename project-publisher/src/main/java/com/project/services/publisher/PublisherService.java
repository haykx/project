package com.project.services.publisher;

import com.project.entities.model.Publisher;
import com.project.entities.reg.PublisherPrincipal;
import com.project.repositories.PublisherRepository;
import com.project.entities.request.PublisherRequest;
import com.project.entities.request.PublisherUpdateDto;
import com.project.entities.response.PublisherResponse;
import com.project.services.exceptions.BadRequestException;
import com.project.services.exceptions.NotFoundException;
import com.project.services.query.PublisherQuery;
import com.project.services.specification.PublisherSpecificationBuilder;
import com.project.services.token.AuthFacade;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final AuthFacade facade;
    private final PublisherRepository repository;

    @Override
    public PublisherResponse add(final PublisherRequest request) {
        PublisherPrincipal principal = (PublisherPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(this.repository.existsByOriginalId(principal.getId())){
            return this.mapper.toResponse(this.repository.findByOriginalId(principal.getId()).orElseThrow());
        }
        if (request == null) {
            throw new BadRequestException("Invalid request");
        }
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    @Override
    public PublisherResponse get(final UUID id) {
        return this.mapper.toResponse(this.repository.findById(id).orElseThrow());
    }

    @Override
    @Transactional
    public PublisherResponse update(final UUID id, final PublisherUpdateDto dto) {
        Publisher publisher = this.repository.findById(id).orElseThrow();
        Optional.ofNullable(dto.getFirstName()).ifPresent(publisher::setFirstName);
        Optional.ofNullable(dto.getLastName()).ifPresent(publisher::setLastName);
        Optional.ofNullable(dto.getDateOfBirth()).ifPresent(publisher::setDateOfBirth);
        return this.mapper.toResponse(this.repository.save(publisher));
    }

    @Override
    @Transactional
    public List<PublisherResponse> search(final PublisherQuery query) {
        return this.repository.findAll(
                specificationBuilder.searchPublishers(query),
                query.getPageable()
        ).getContent().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(final UUID id) {
        this.repository.deleteById(id);
    }

    public PublisherResponse me() {
        return this.mapper.toResponse(this.repository.findByOriginalId(this.facade.getOriginalId()).orElseThrow(()->new NotFoundException("")));
    }
}
