package com.project.services;

import java.util.List;
import java.util.UUID;

public interface CrudService<Request, Response>  {
    Response add(Request request);
    Response get(UUID id);
    List<Response> get(List<UUID> ids);
    void delete(UUID id);
}
