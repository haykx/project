package com.project.publisher.api.filters;

import com.project.publisher.reg.PublisherPrincipal;
import com.project.publisher.services.token.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.project.publisher.services.token.JwtUtil.TOKEN_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

public class UserAuthorizationFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;
  public UserAuthorizationFilter(final JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader(AUTHORIZATION);
    if(authHeader != null && authHeader.startsWith(TOKEN_PREFIX)){
      try {
        String token = authHeader.substring(TOKEN_PREFIX.length());
        PublisherPrincipal principal = jwtUtil.getPrincipalFromToken(token);
        UsernamePasswordAuthenticationToken authenticationToken
          = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
      }
      catch (Exception ex){
        response.sendError(FORBIDDEN.value());
      }
    }
    else {
      filterChain.doFilter(request, response);
    }
  }
}
