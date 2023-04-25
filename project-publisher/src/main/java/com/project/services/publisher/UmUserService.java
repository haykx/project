package com.project.services.publisher;

import com.project.entities.request.UmPublisherUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UmUserService<Request, Response> {
    Response update(UUID id, UmPublisherUpdateDto dto);

    ResponseEntity<?> add(Request request);

    Response get(UUID id);

    List<Response> get(List<UUID> ids);

    void delete(UUID id);

}
