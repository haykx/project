package com.project.um.reg;

import java.util.Set;

public interface UserPrincipal {

  Set<String> getAuthorities();
  String getUsername();
  String getPassword();
}
