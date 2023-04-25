package com.project.services.mapper;

public interface Mapper<Request, Entity, Response>{
    Entity toEntity(Request request);
    Response toResponse(Entity entity);
}
