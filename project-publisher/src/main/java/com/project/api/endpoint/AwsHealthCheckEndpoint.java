package com.project.api.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
public class AwsHealthCheckEndpoint {
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
