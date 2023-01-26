package com.project.um.api.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.um.reg.LoginRequest;
import com.project.um.reg.PublisherPrincipal;
import com.project.um.services.token.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  @Autowired
  private JwtUtil jwtUtil;
  private final AuthenticationManager authenticationManager;
  public UserAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    LoginRequest dto = (LoginRequest) request;
    String email = dto.getEmail();
    String password = dto.getPassword();
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(new PublisherPrincipal(null, email, null), password);
    return authenticationManager.authenticate(authenticationToken);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
    PublisherPrincipal user = (PublisherPrincipal) authentication.getPrincipal();

    Map<String, String> tokens = jwtUtil.generateToken(user);

    response.setContentType(APPLICATION_JSON_VALUE);
    new ObjectMapper().writeValue(response.getOutputStream(),tokens);
  }
}
