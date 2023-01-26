package com.project.um.request;


import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RoleRequest {
  private String name;

  private List<UUID> permissions;
}
