package com.project.um.services.token;

import java.util.Map;

public interface TokenService {
  Map<String, String> generateTokens(String email);
}
