package com.project.publisher.api.config;

import com.project.publisher.api.entryPoint.JwtAuthEntryPoint;
import com.project.publisher.api.filters.UserAuthorizationFilter;
import com.project.publisher.services.token.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final JwtUtil jwtUtil;
  private final JwtAuthEntryPoint entryPoint;


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    return http
            .csrf()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(STATELESS)
            .and()
//            .authorizeHttpRequests()
//            .requestMatchers("/api/v1/publisher/main/**")
//            .permitAll()
//            .anyRequest()
//            .authenticated()
//            .and()
            .exceptionHandling()
            .authenticationEntryPoint(entryPoint)
            .and()
            .addFilterBefore(new UserAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
            .build();
  }
}
