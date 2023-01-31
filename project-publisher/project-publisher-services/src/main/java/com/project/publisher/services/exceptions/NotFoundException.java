package com.project.publisher.services.exceptions;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class NotFoundException extends ApplicationException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public NotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, String.format("Recourse with id : {%s} not found", id.toString()));
    }
}
