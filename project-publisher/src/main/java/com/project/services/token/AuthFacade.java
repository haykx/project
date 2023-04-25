package com.project.services.token;

import com.project.entities.reg.PublisherPrincipal;
import com.project.repositories.PublisherRepository;
import com.project.services.exceptions.BadRequestException;
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
