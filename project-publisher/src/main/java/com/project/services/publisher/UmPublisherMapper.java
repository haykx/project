package com.project.services.publisher;

import com.project.entities.model.UmPublisher;
import com.project.entities.reg.PublisherPrincipal;
import com.project.entities.request.UmPublisherRequest;
import com.project.entities.response.UmPublisherResponse;
import com.project.repositories.RoleRepository;
import com.project.services.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UmPublisherMapper implements Mapper<UmPublisherRequest, UmPublisher, UmPublisherResponse> {
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @Override
    public UmPublisher toEntity(UmPublisherRequest request) {
        UmPublisher umPublisher = new UmPublisher();
        umPublisher.setEmail(request.getEmail());
        umPublisher.setPassword(this.encoder.encode(request.getPassword()));
        umPublisher.setRoles(this.roleRepository.getDefaultRoles());
        return umPublisher;
    }

    @Override
    public UmPublisherResponse toResponse(UmPublisher umPublisher) {
        UmPublisherResponse response = new UmPublisherResponse();
        response.setId(umPublisher.getId());
        response.setEmail(umPublisher.getEmail());
        response.setCreated(umPublisher.getCreated());
        response.setUpdated(umPublisher.getUpdated());
        return response;
    }

    public PublisherPrincipal toPrincipal(UmPublisher umPublisher) {
        PublisherPrincipal principal = new PublisherPrincipal();
        principal.setId(umPublisher.getId());
        principal.setEmail(umPublisher.getEmail());
        principal.setPassword(umPublisher.getPassword());
        principal.setAuthorities(umPublisher.getRoles().stream().flatMap(r -> r.getPermissions().stream()).map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet()));
        return principal;
    }
}
