package com.project.entities.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class BaseResponse {
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime updated;
}
