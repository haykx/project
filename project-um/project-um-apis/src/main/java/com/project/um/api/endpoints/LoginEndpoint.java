package com.project.um.api.endpoints;

import com.project.um.reg.LoginRequest;
import com.project.um.services.publisher.PublisherLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginEndpoint {
    private final PublisherLoginService service;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        return this.service.login(request);
    }
}
