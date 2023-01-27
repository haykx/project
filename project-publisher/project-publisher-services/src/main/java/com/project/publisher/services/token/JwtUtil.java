package com.project.publisher.services.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.publisher.reg.PublisherPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
  @Value("${jwt.secret}")
  private String secret;
  public static String TOKEN_PREFIX = "Bearer ";

  public PublisherPrincipal getPrincipalFromToken(String token){
    UUID id = this.getIdFromToken(token);
    String username = this.getUsernameFromToken(token);
    Set<SimpleGrantedAuthority> permissions = this.getClaimFromToken(token, claims -> (List<String>) claims.get("permissions")).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    return new PublisherPrincipal(id, username, null, permissions);
  }

  private String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  private DecodedJWT decode(String token){
    return JWT.require(Algorithm.HMAC256(secret.getBytes())).build().verify(token);
  }

  private Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  private  <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
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

//  public <T extends PublisherPrincipal> Map<String, String> generateToken(T user) {
//    final Date createdDate = new Date();
//    final Date expirationDate = calculateExpirationDate(createdDate, 1000L*60*60*24);
//    final Date refreshExpirationDate = calculateExpirationDate(createdDate, 1000L*60*60*24*30);
//
//    Claims claims = Jwts.claims().setSubject(user.getUsername()).setId(user.getId().toString());
//    claims.put("permissions", user.getAuthorities());
//
//    String accessToken = Jwts.builder()
//      .setClaims(claims)
//      .setSubject(user.getUsername())
//      .setIssuedAt(createdDate)
//      .setExpiration(expirationDate)
//      .signWith(SignatureAlgorithm.HS256, secret.getBytes())
//      .compact();
//
//    String refresh_token = Jwts.builder()
//      .setClaims(claims)
//      .setSubject(user.getUsername())
//      .setIssuedAt(createdDate)
//      .setExpiration(refreshExpirationDate)
//      .signWith(SignatureAlgorithm.HS256, secret.getBytes())
//      .compact();
//
//    return Map.of(
//      "access_token", accessToken,
//      "refresh_token", refresh_token
//    );
//
//  }
  public Boolean validateToken(String token, String username) {
    final String usernameToken = getUsernameFromToken(token);
    return (usernameToken.equals(username) && !isTokenExpired(token));
  }

//  private Date calculateExpirationDate(Date createdDate, long multiplier) {
//    return new Date(createdDate.getTime() + expiration * multiplier);
//  }

  private UUID getIdFromToken(String token) {
    return getClaimFromToken(token, claims -> UUID.fromString((String) claims.get("id")));
  }
}