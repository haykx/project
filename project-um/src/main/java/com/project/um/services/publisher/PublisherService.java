package com.project.um.services.publisher;

import com.project.um.entities.model.UmPublisher;
import com.project.um.entities.request.PublisherRequest;
import com.project.um.entities.request.PublisherUpdateDto;
import com.project.um.entities.response.PublisherResponse;
import com.project.um.repositories.PublisherRepository;
import com.project.um.services.exceptions.BadRequestException;
import com.project.um.services.exceptions.NotFoundException;
import com.project.um.services.token.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherService implements UserService <PublisherRequest, PublisherResponse> {

    private final PublisherMapper mapper;
    private final PublisherRepository repository;
    private final PasswordEncoder encoder;
    private final JwtTokenService loginService;

    @Override
    public ResponseEntity<?> add(PublisherRequest request) {
        if (repository.existsByEmail(request.getEmail())){
            throw new BadRequestException("You already got an account!");
        }
        UmPublisher publisher = this.repository.save(this.mapper.toEntity(request));
        Map<String, String> tokens = this.loginService.generateTokens(this.mapper.toPrincipal(publisher));
        return ResponseEntity.ok(tokens);
    }

    @Override
    public PublisherResponse update(UUID id, PublisherUpdateDto dto) {
        UmPublisher umPublisher = this.repository.findById(id).orElseThrow(()->new NotFoundException(id));
        Optional.ofNullable(dto.getEmail()).ifPresent(email -> {
            if(!this.repository.existsByEmail(email)){
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
        return this.mapper.toResponse(this.repository.findById(id).orElseThrow(()->new NotFoundException(id)));
    }

    @Override
    public List<PublisherResponse> get(List<UUID> ids) {
        return this.repository.findAllByIdIn(ids).stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        this.repository.deleteById(id);
    }


}
