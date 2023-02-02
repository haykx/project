package com.project.um.services.token;

import com.project.um.reg.PublisherPrincipal;
import com.project.um.repositories.PublisherRepository;
import com.project.um.services.exceptions.NotFoundException;
import com.project.um.services.publisher.PublisherMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenService implements TokenService<PublisherPrincipal> {
  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private Long expiration;
  private final PublisherRepository repository;
  private final PublisherMapper mapper;

  @Override
  public Map<String, String> generateTokens(String email) {
    return this.generate(this.getPrincipal(email));
  }

  public Map<String, String> generateTokens(PublisherPrincipal principal) {
    return this.generate(principal);
  }

  private PublisherPrincipal getPrincipal(String email) {
    return this.mapper.toPrincipal(this.repository.findByEmailAndDeletedIsNull(email).orElseThrow(()->new NotFoundException(email)));
  }

  private <T extends PublisherPrincipal> Map<String, String> generate(T user) {
    final Date createdDate = new Date();
    final Date expirationDate = calculateExpirationDate(createdDate, 1000L*60*60*24);
    final Date refreshExpirationDate = calculateExpirationDate(createdDate, 1000L*60*60*24*30);

    Claims claims = Jwts.claims()
            .setSubject(user.getUsername())
            .setId(user.getId().toString())
            .setIssuedAt(createdDate);
    claims.put("permissions", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

    String accessToken = Jwts.builder()
            .setClaims(claims)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secret.getBytes())
            .compact();

    String refresh_token = Jwts.builder()
            .setClaims(claims)
            .setExpiration(refreshExpirationDate)
            .signWith(SignatureAlgorithm.HS256, secret.getBytes())
            .compact();

    return Map.of(
            "access_token", accessToken,
            "refresh_token", refresh_token
    );

  }

  private Date calculateExpirationDate(Date createdDate, long multiplier) {
    return new Date(createdDate.getTime() + expiration * multiplier);
  }
}
