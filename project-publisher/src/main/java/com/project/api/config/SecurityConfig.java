package com.project.api.config;

import com.project.api.entryPoint.JwtAuthEntryPoint;
import com.project.api.filters.UserAuthenticationFilter;
import com.project.api.filters.UserAuthorizationFilter;
import com.project.services.details.PublisherUserDetailsService;
import com.project.services.token.JwtTokenService;
import com.project.services.token.JwtUtil;
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

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final JwtTokenService tokenService;
    private final JwtAuthEntryPoint entryPoint;
    private final DaoAuthenticationProvider authProvider;
    private final PublisherUserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("**/um/**", "**/health")
                .permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .and()
                .authenticationProvider(authProvider)
                .addFilterBefore(new UserAuthorizationFilter(jwtUtil), UserAuthenticationFilter.class)
                .addFilter(new UserAuthenticationFilter(tokenService, userDetailsService, encoder))
                .build();
    }
}


//package com.project.publisher.api.config;
//
//import com.project.publisher.api.entryPoint.JwtAuthEntryPoint;
//import com.project.publisher.api.filters.UserAuthorizationFilter;
//import com.project.publisher.services.token.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//  private final JwtUtil jwtUtil;
//  private final JwtAuthEntryPoint entryPoint;
//
//
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//    return http
//            .cors()
//            .and()
//            .csrf()
//            .disable()
//            .sessionManagement()
//            .sessionCreationPolicy(STATELESS)
//            .and()
//            .exceptionHandling()
//            .authenticationEntryPoint(entryPoint)
//            .and()
//            .addFilterBefore(new UserAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
//            .build();
//  }
//}
