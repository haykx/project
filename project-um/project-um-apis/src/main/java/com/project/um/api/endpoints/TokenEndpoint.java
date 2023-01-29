package com.project.um.api.endpoints;

import com.project.um.reg.PublisherPrincipal;
import com.project.um.services.token.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenEndpoint {
  private final JwtTokenService tokenService;

  @GetMapping
  public Map<String, String> updateExpiredTokenCustomerUser(){
    PublisherPrincipal principal  = (PublisherPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return tokenService.generateTokens(principal.getEmail());
  }
}
