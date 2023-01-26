package com.project.um.reg;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.um.entities.Permission;
import com.project.um.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherPrincipal implements UserPrincipal {
    private UUID id;
    private String email;
    @JsonIgnore
    private List<Role> roles;

    @Override
    public Set<String> getAuthorities() {
        return this.roles.stream().flatMap(role -> role.getPermissions().stream()).map(Permission::getName).collect(Collectors.toSet());
    }

    @Override
    public String getEmail() {
        return this.email;
    }
}
