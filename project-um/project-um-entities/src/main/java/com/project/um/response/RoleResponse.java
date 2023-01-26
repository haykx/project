package com.project.um.response;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleResponse extends BaseResponse {
  private String name;
}
