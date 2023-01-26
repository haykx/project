package com.project.um.api.config;

import com.project.um.api.entryPoint.JwtAuthEntryPoint;
import com.project.um.api.filters.UserAuthenticationFilter;
import com.project.um.api.filters.UserAuthorizationFilter;
import com.project.um.services.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;
  private final JwtAuthEntryPoint entryPoint;

  @Autowired
  public SecurityConfig(@Lazy AuthenticationManager authenticationManager, JwtUtil jwtUtil, JwtAuthEntryPoint entryPoint) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
    this.entryPoint = entryPoint;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    return http
            .csrf()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(STATELESS)
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(entryPoint)
            .and()
            .addFilter(new UserAuthenticationFilter(authenticationManager))
            .addFilterBefore(new UserAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
            .build();
  }
}
