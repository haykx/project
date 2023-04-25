package com.project.um.services.publisher;

import com.project.um.entities.request.PublisherUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserService<Request, Response> {
    Response update(UUID id, PublisherUpdateDto dto);
    ResponseEntity<?> add(Request request);
    Response get(UUID id);
    List<Response> get(List<UUID> ids);
    void delete(UUID id);

}
