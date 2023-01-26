package com.project.um.services.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.um.reg.PublisherPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtUtil {
  @Value("${jwt.secret}")
  private String secret;
  @Value("${jwt.expiration}")
  private Long expiration;
  public static String TOKEN_PREFIX = "Bearer ";



  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public DecodedJWT decode(String token){
    return JWT.require(Algorithm.HMAC256(secret.getBytes())).build().verify(token);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
      .setSigningKey(secret.getBytes())
      .parseClaimsJws(token)
      .getBody();
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);

    return expiration.before(new Date());
  }

  public <T extends PublisherPrincipal> Map<String, String> generateToken(T user) {
    final Date createdDate = new Date();
    final Date expirationDate = calculateExpirationDate(createdDate, 1000L*60*60*24);
    final Date refreshExpirationDate = calculateExpirationDate(createdDate, 1000L*60*60*24*30);

    Claims claims = Jwts.claims().setSubject(user.getEmail()).setId(user.getId().toString());
    claims.put("permissions", user.getAuthorities());

    String accessToken = Jwts.builder()
      .setClaims(claims)
      .setSubject(user.getEmail())
      .setIssuedAt(createdDate)
      .setExpiration(expirationDate)
      .signWith(SignatureAlgorithm.HS256, secret.getBytes())
      .compact();

    String refresh_token = Jwts.builder()
      .setClaims(claims)
      .setSubject(user.getEmail())
      .setIssuedAt(createdDate)
      .setExpiration(refreshExpirationDate)
      .signWith(SignatureAlgorithm.HS256, secret.getBytes())
      .compact();

    return Map.of(
      "access_token", accessToken,
      "refresh_token", refresh_token
    );

  }
  public Boolean validateToken(String token, String username) {
    final String usernameToken = getUsernameFromToken(token);
    return (usernameToken.equals(username) && !isTokenExpired(token));
  }

  private Date calculateExpirationDate(Date createdDate, long multiplier) {
    return new Date(createdDate.getTime() + expiration * multiplier);
  }

  public UUID getIdFromToken(String token) {
    return getClaimFromToken(token, claims -> UUID.fromString((String) claims.get("id")));
  }
}