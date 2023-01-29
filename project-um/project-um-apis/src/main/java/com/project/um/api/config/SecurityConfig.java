package com.project.um.api.config;

import com.project.um.api.entryPoint.JwtAuthEntryPoint;
import com.project.um.api.filters.UserAuthenticationFilter;
import com.project.um.api.filters.UserAuthorizationFilter;
import com.project.um.services.details.PublisherUserDetailsService;
import com.project.um.services.token.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@EnableAutoConfiguration(exclude=ErrorMvcAutoConfiguration.class)
public class SecurityConfig {
  private final JwtUtil jwtUtil;
  private final JwtAuthEntryPoint entryPoint;
  private final DaoAuthenticationProvider authProvider;
  private final PublisherUserDetailsService userDetailsService;
  private final PasswordEncoder encoder;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    return http
            .csrf()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(STATELESS)
            .and()
            .authorizeHttpRequests()
//            .requestMatchers(POST, "**/publisher")
//            .permitAll()
            .anyRequest()
            .permitAll()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(entryPoint)
            .and()
            .authenticationProvider(authProvider)
            .addFilter(new UserAuthenticationFilter(jwtUtil, userDetailsService, encoder))
            .addFilterBefore(new UserAuthorizationFilter(jwtUtil), UserAuthenticationFilter.class)
            .build();
  }
}
