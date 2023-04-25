package com.project.um.services.login;

import org.springframework.http.ResponseEntity;

public interface LoginService<T> {
    ResponseEntity<?> login(T request);
}
