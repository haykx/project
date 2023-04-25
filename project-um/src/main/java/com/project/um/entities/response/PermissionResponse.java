package com.project.um.entities.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionResponse extends BaseResponse{
  private String name;
}
