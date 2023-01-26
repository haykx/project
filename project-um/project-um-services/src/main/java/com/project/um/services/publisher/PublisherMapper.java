package com.project.um.services.publisher;

import com.project.um.entities.Publisher;
import com.project.um.reg.PublisherPrincipal;
import com.project.um.request.PublisherRequest;
import com.project.um.response.PublisherResponse;
import com.project.um.services.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublisherMapper implements Mapper<PublisherRequest, Publisher, PublisherResponse> {
    private final PasswordEncoder encoder;
    @Override
    public Publisher toEntity(PublisherRequest request) {
        Publisher publisher = new Publisher();
        publisher.setFirstName(request.getFirstName());
        publisher.setLastName(request.getLastName());
        publisher.setEmail(request.getEmail());
        publisher.setPassword(encoder.encode(request.getPassword()));
        publisher.setBio(publisher.getBio());
        publisher.setAvatar(request.getAvatar());
        return publisher;
    }

    @Override
    public PublisherResponse toResponse(Publisher publisher) {
        PublisherResponse response = new PublisherResponse();
        response.setId(publisher.getId());
        response.setFirstName(publisher.getFirstName());
        response.setLastName(publisher.getLastName());
        response.setEmail(response.getEmail());
        response.setBio(publisher.getBio());
        response.setAvatar(response.getAvatar());
        response.setCreated(response.getCreated());
        response.setUpdated(response.getUpdated());
        response.setDeleted(response.getDeleted());
        return response;
    }

    public PublisherPrincipal toPrincipal(Publisher publisher){
        PublisherPrincipal principal = new PublisherPrincipal();
        principal.setId(publisher.getId());
        principal.setEmail(publisher.getEmail());
        principal.setPassword(publisher.getPassword());
        principal.setRoles(publisher.getRoles());
        return principal;
    }
}
