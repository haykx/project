package com.project.um.services.publisher;

import com.project.um.entities.UmPublisher;
import com.project.um.reg.PublisherPrincipal;
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
    @Override
    public UmPublisher toEntity(PublisherRequest request) {
        UmPublisher umPublisher = new UmPublisher();
        umPublisher.setEmail(request.getEmail());
        umPublisher.setPassword(encoder.encode(request.getPassword()));
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
        principal.setRoles(umPublisher.getRoles());
        return principal;
    }
}
