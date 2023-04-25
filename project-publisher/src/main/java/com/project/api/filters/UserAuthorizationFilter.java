package com.project.api.filters;

import com.project.entities.reg.PublisherPrincipal;
import com.project.services.token.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RequiredArgsConstructor
public class UserAuthorizationFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    if (request.getServletPath().equals("api/v1/login")) {
      filterChain.doFilter(request, response);
    } else {
      String authHeader = request.getHeader(AUTHORIZATION);
      if (authHeader != null && authHeader.startsWith(JwtUtil.TOKEN_PREFIX)) {
        try {
          String token = authHeader.substring(JwtUtil.TOKEN_PREFIX.length());
          PublisherPrincipal principal = jwtUtil.getPrincipalFromToken(token);
          UsernamePasswordAuthenticationToken authenticationToken
                  = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          filterChain.doFilter(request, response);
        } catch (Exception ex) {
          response.sendError(FORBIDDEN.value());
        }
      } else {
        filterChain.doFilter(request, response);
      }
    }
  }
}
