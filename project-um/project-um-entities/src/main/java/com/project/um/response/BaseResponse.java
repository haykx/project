package com.project.um.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseResponse {
    private UUID id;

    private LocalDateTime created;

    private LocalDateTime updated;

    private LocalDateTime deleted;
}
