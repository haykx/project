package com.project.publisher.api.endpoint;

import com.project.publisher.request.SurveyRequest;
import com.project.publisher.response.SurveyResponse;
import com.project.publisher.services.query.SurveyQuery;
import com.project.publisher.services.survey.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyEndpoint {
    private final SurveyService service;

    @PostMapping
    public SurveyResponse add(@RequestBody final SurveyRequest request) {
        return this.service.add(request);
    }

    @GetMapping("/{id}")
    public SurveyResponse get(@PathVariable("id") final UUID id) {
        return this.service.get(id);
    }

    @GetMapping
    public List<SurveyResponse> search(final SurveyQuery query) {
        return this.service.search(query);
    }

    @DeleteMapping
    public void delete(@RequestParam("id") final UUID id) {
        this.service.delete(id);
    }
}
