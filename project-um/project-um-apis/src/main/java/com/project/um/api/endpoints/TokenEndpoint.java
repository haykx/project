package com.project.um.api.endpoints;

import com.project.um.reg.PublisherPrincipal;
import com.project.um.services.token.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class TokenEndpoint {
  private final JwtTokenService tokenService;

  @GetMapping("/token")
  public Map<String, String> updateExpiredTokenCustomerUser(){
    PublisherPrincipal principal  = (PublisherPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return tokenService.generateTokens(principal.getUsername());
  }
}
