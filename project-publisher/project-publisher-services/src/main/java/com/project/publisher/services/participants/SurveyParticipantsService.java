package com.project.publisher.services.participants;

import com.project.publisher.repositories.SurveyParticipantsRepository;
import com.project.publisher.request.SurveyParticipantRequest;
import com.project.publisher.response.SurveyParticipantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SurveyParticipantsService {
    private final SurveyParticipantsRepository repository;
    private final SurveyParticipantMapper mapper;

    public SurveyParticipantResponse add(@Valid SurveyParticipantRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    public SurveyParticipantResponse get(@NotNull UUID id) {
        return this.mapper.toResponse(this.repository.findById(id).orElseThrow());
    }
}
