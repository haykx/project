package com.project.publisher.services.survey;

import com.project.publisher.repositories.SurveyRepository;
import com.project.publisher.request.SurveyRequest;
import com.project.publisher.response.SurveyResponse;
import com.project.publisher.services.exceptions.NotFoundException;
import com.project.publisher.services.query.SurveyQuery;
import com.project.publisher.services.specification.SurveySpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository repository;
    private final SurveyMapper mapper;
    private final SurveySpecificationBuilder specificationBuilder;

    public SurveyResponse add(SurveyRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    public SurveyResponse get(UUID id) {
        return this.mapper.toResponse(this.repository.findById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    public void delete(UUID id) {
        this.repository.deleteById(id);
    }

    public List<SurveyResponse> search(SurveyQuery query) {
        return this.repository.findAll(
                specificationBuilder.searchPosts(query),
                query.getPageable()
        ).getContent().stream().map(mapper::toResponse).collect(Collectors.toList());
    }
}
