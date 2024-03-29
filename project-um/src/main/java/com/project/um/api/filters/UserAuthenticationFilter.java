package com.project.um.api.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.um.entities.reg.LoginRequest;
import com.project.um.entities.reg.PublisherPrincipal;
import com.project.um.services.details.PublisherUserDetailsService;
import com.project.um.services.exceptions.BadRequestException;
import com.project.um.services.token.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtTokenService tokenService;
    private final PublisherUserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest dto = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            String email = dto.getEmail();
            String password = dto.getPassword();
            UserDetails principal = this.userDetailsService.loadUserByUsername(email);
            if (encoder.matches(password, principal.getPassword())) {
                return new UsernamePasswordAuthenticationToken(principal, password);
            }
            throw new BadRequestException("Wrong password");
        } catch (Exception e) {
            throw new BadRequestException("Invalid credentials");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        PublisherPrincipal user = (PublisherPrincipal) authentication.getPrincipal();
        Map<String, String> tokens = tokenService.generateTokens(user);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
