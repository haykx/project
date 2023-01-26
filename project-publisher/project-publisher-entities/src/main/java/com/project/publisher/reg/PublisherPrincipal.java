package com.project.publisher.reg;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherPrincipal implements UserPrincipal {
    private UUID id;
    private String email;

    @Override
    public String getUsername() {
        return this.email;
    }
}
