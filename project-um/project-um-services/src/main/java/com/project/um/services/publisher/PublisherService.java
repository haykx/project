package com.project.um.services.publisher;

import com.project.um.entities.UmPublisher;
import com.project.um.repositories.PublisherRepository;
import com.project.um.repositories.PublisherRoleRepository;
import com.project.um.request.PublisherRequest;
import com.project.um.request.PublisherUpdateDto;
import com.project.um.response.PublisherResponse;
import com.project.um.services.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherService implements UserService {

    private final PublisherMapper mapper;
    private final PublisherRepository repository;
    private final PublisherRoleRepository crossRepository;
    private final PasswordEncoder encoder;

    @Override
    public PublisherResponse add(PublisherRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    @Override
    public PublisherResponse update(UUID id, PublisherUpdateDto dto) {
        UmPublisher umPublisher = this.repository.findByIdAndDeletedIsNull(id).orElseThrow(()->new NotFoundException(id));
        Optional.ofNullable(dto.getEmail()).ifPresent(email -> {
            if(!this.repository.existsByEmailAndDeletedIsNull(email)){
                umPublisher.setEmail(email);
            }
        });
        Optional.ofNullable(dto.getPassword()).ifPresent(password -> {
            umPublisher.setPassword(this.encoder.encode(password));
        });
        return this.mapper.toResponse(this.repository.save(umPublisher));
    }

    @Override
    public PublisherResponse get(UUID id) {
        return this.mapper.toResponse(this.repository.findByIdAndDeletedIsNull(id).orElseThrow(()->new NotFoundException(id)));
    }

    @Override
    public List<PublisherResponse> get(List<UUID> ids) {
        return this.repository.findAllByIdInAndDeletedIsNull(ids).stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        this.crossRepository.deleteAllByPk_PublisherId(id);
        this.repository.delete(id);
    }


}
