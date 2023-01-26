package com.project.um.services.publisher;

import com.project.um.request.PublisherRequest;
import com.project.um.request.PublisherUpdateDto;
import com.project.um.response.PublisherResponse;
import com.project.um.services.CrudService;

import java.util.UUID;

public interface UserService extends CrudService<PublisherRequest, PublisherResponse> {
    PublisherResponse update(UUID id, PublisherUpdateDto dto);
}
