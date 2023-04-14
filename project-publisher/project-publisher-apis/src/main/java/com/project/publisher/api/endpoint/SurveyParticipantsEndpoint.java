package com.project.publisher.api.endpoint;

import com.project.publisher.request.SurveyParticipantRequest;
import com.project.publisher.response.SurveyParticipantResponse;
import com.project.publisher.services.participants.SurveyParticipantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/survey-participants")
public class SurveyParticipantsEndpoint {
    private final SurveyParticipantsService service;

    @PostMapping
    public SurveyParticipantResponse add(@RequestBody final SurveyParticipantRequest request) {
        return this.service.add(request);
    }

    @GetMapping("/{id}")
    public SurveyParticipantResponse get(@PathVariable("id") final UUID id) {
        return this.service.get(id);
    }

}
