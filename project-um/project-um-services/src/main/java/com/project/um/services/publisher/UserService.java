package com.project.um.services.publisher;

import java.util.List;
import java.util.UUID;

public interface UserService<Request, Response, Update> {
    Response add(Request request);
    Response update(UUID id, Update dto);
    Response get(UUID id);
    List<Response> get(List<UUID> ids);
    void delete(UUID id);
}
