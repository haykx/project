package com.project.publisher.services.token;

import com.project.publisher.reg.PublisherPrincipal;
import com.project.publisher.repositories.PublisherRepository;
import com.project.publisher.services.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final PublisherRepository repository;

    public PublisherPrincipal getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null) {
            throw new BadRequestException("");
        }
        return (PublisherPrincipal) principal;
    }

    public UUID getOriginalId() {
        return this.getPrincipal().getId();
    }
}
