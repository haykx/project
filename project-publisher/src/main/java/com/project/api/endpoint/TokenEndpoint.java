package com.project.api.endpoint;

import com.project.entities.reg.PublisherPrincipal;
import com.project.services.token.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/um/token")
public class TokenEndpoint {
  private final JwtTokenService tokenService;

  @GetMapping
  public Map<String, String> updateExpiredTokenCustomerUser() {
    PublisherPrincipal principal = (PublisherPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return tokenService.generateTokens(principal.getEmail());
  }
}
