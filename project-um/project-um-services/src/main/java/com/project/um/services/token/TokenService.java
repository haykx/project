package com.project.um.services.token;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface TokenService<T extends UserDetails>  {
  Map<String, String> generateTokens(String email);

  Map<String, String> generateTokens(T principal);
}
