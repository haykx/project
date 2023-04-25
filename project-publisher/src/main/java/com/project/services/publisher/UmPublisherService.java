package com.project.services.publisher;

import com.project.entities.model.UmPublisher;
import com.project.entities.request.UmPublisherRequest;
import com.project.entities.request.UmPublisherUpdateDto;
import com.project.entities.response.UmPublisherResponse;
import com.project.repositories.UmPublisherRepository;
import com.project.services.exceptions.BadRequestException;
import com.project.services.exceptions.NotFoundException;
import com.project.services.token.JwtTokenService;
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
public class UmPublisherService implements UmUserService<UmPublisherRequest, UmPublisherResponse> {

    private final UmPublisherMapper mapper;
    private final UmPublisherRepository repository;
    private final PasswordEncoder encoder;
    private final JwtTokenService loginService;

    @Override
    public ResponseEntity<?> add(UmPublisherRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("You already got an account!");
        }
        UmPublisher publisher = this.repository.save(this.mapper.toEntity(request));
        Map<String, String> tokens = this.loginService.generateTokens(this.mapper.toPrincipal(publisher));
        return ResponseEntity.ok(tokens);
    }

    @Override
    public UmPublisherResponse update(UUID id, UmPublisherUpdateDto dto) {
        UmPublisher umPublisher = this.repository.findById(id).orElseThrow(() -> new NotFoundException(id));
        Optional.ofNullable(dto.getEmail()).ifPresent(email -> {
            if (!this.repository.existsByEmail(email)) {
                umPublisher.setEmail(email);
            }
        });
        Optional.ofNullable(dto.getPassword()).ifPresent(password -> {
            umPublisher.setPassword(this.encoder.encode(password));
        });
        return this.mapper.toResponse(this.repository.save(umPublisher));
    }

    @Override
    public UmPublisherResponse get(UUID id) {
        return this.mapper.toResponse(this.repository.findById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    @Override
    public List<UmPublisherResponse> get(List<UUID> ids) {
        return this.repository.findAllByIdIn(ids).stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        this.repository.deleteById(id);
    }


}
