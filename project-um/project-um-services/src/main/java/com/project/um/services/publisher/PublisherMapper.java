package com.project.um.services.publisher;

import com.project.um.entities.UmPublisher;
import com.project.um.reg.PublisherPrincipal;
import com.project.um.repositories.RoleRepository;
import com.project.um.request.PublisherRequest;
import com.project.um.response.PublisherResponse;
import com.project.um.services.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublisherMapper implements Mapper<PublisherRequest, UmPublisher, PublisherResponse> {
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;
    @Override
    public UmPublisher toEntity(PublisherRequest request) {
        UmPublisher umPublisher = new UmPublisher();
        umPublisher.setEmail(request.getEmail());
        umPublisher.setPassword(this.encoder.encode(request.getPassword()));
        umPublisher.setRoles(this.roleRepository.getDefaultRoles());
        return umPublisher;
    }

    @Override
    public PublisherResponse toResponse(UmPublisher umPublisher) {
        PublisherResponse response = new PublisherResponse();
        response.setId(umPublisher.getId());
        response.setEmail(umPublisher.getEmail());
        response.setCreated(umPublisher.getCreated());
        response.setUpdated(umPublisher.getUpdated());
        response.setDeleted(umPublisher.getDeleted());
        return response;
    }

    public PublisherPrincipal toPrincipal(UmPublisher umPublisher){
        PublisherPrincipal principal = new PublisherPrincipal();
        principal.setId(umPublisher.getId());
        principal.setEmail(umPublisher.getEmail());
        principal.setPassword(umPublisher.getPassword());
        principal.setRoles(umPublisher.getRoles());
        return principal;
    }
}
