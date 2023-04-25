package com.project.um.services.exceptions;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class NotFoundException extends ApplicationException {
    public NotFoundException(String email) {
        super(HttpStatus.NOT_FOUND, String.format("Recourse with email {%s} not found", email));
    }

    public NotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, String.format("Recourse with id  {%s} not found", id.toString()));
    }
}
