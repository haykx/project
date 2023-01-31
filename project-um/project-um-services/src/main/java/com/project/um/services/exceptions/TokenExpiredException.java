package com.project.um.services.exceptions;

import org.springframework.http.HttpStatus;

public class TokenExpiredException extends ApplicationException {
    public TokenExpiredException() {
        super(HttpStatus.FORBIDDEN, "Token is expired");
    }

}
