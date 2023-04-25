package com.project.api.endpoint;

import com.project.entities.response.PublicationResponse;
import com.project.services.publication.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher/publication")
public class PublicationEndpoint {
    private final PublicationService service;

    @GetMapping
    public List<PublicationResponse> getAll() {
        return this.service.getAll();
    }
}
