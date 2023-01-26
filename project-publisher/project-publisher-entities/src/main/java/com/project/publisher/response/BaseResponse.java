package com.project.publisher.response;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseResponse {
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime deleted;
}
