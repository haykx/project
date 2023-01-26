package com.project.publisher.api.filters;

import com.project.publisher.services.token.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
    if(request.getServletPath().equals("api/customer-user/login") || request.getServletPath().equals("api/bank-user/login")){
      filterChain.doFilter(request, response);
    }
    else {
      String authHeader = request.getHeader(AUTHORIZATION);
      if(authHeader != null && authHeader.startsWith(TOKEN_PREFIX)){
        try {
          String token = authHeader.substring(TOKEN_PREFIX.length());
          UUID id = jwtUtil.getIdFromToken(token);
          String username = jwtUtil.getUsernameFromToken(token);
          Set<SimpleGrantedAuthority> authorities = jwtUtil.getClaimFromToken(token, claims -> (List<String>) claims.get("permissions")).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());

          UsernamePasswordAuthenticationToken authenticationToken
            = new UsernamePasswordAuthenticationToken(username, null, authorities);
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
}
