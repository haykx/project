package com.project.um.services.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.um.reg.PublisherPrincipal;
import com.project.um.services.exceptions.TokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private Long expiration;
  public static String TOKEN_PREFIX = "Bearer ";

  public PublisherPrincipal getPrincipalFromToken(String token){
    final Claims claims = this.getAllClaimsFromToken(token);
    if (this.isTokenExpired(claims)){
      throw new TokenExpiredException();
    }
    final UUID id = this.getIdFromToken(claims);
    final String username = this.getUsernameFromToken(claims);
    final Set<SimpleGrantedAuthority> permissions = this.getPermissions(claims);
    return new PublisherPrincipal(id, username, null, permissions);
  }

  private Set<SimpleGrantedAuthority> getPermissions(final Claims claims) {
    return this.getClaimFromToken(claims, c -> (List<String>) c.get("permissions")).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
  }

  private String getUsernameFromToken(Claims claims) {
    return getClaimFromToken(claims, Claims::getSubject);
  }

  private DecodedJWT decode(String token){
    return JWT.require(Algorithm.HMAC256(secret.getBytes())).build().verify(token);
  }

  private Date getExpirationDateFromToken(Claims claims) {
    return getClaimFromToken(claims, Claims::getExpiration);
  }

  private  <T> T getClaimFromToken(Claims claims, Function<Claims, T> claimsResolver) {
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
      .setSigningKey(secret.getBytes())
      .parseClaimsJws(token)
      .getBody();
  }

  private Boolean isTokenExpired(Claims claims) {
    final Date expiration = getExpirationDateFromToken(claims);
    return expiration.before(new Date());
  }

  public <T extends PublisherPrincipal> Map<String, String> generateToken(T user) {
    final Date createdDate = new Date();
    final Date expirationDate = calculateExpirationDate(createdDate, 1000L*60*60*24);
    final Date refreshExpirationDate = calculateExpirationDate(createdDate, 1000L*60*60*24*30);

    Claims claims = Jwts.claims().setSubject(user.getUsername()).setId(user.getId().toString());
    claims.put("permissions", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

    String accessToken = Jwts.builder()
      .setClaims(claims)
      .setSubject(user.getUsername())
      .setIssuedAt(createdDate)
      .setExpiration(expirationDate)
      .signWith(SignatureAlgorithm.HS256, "secret".getBytes())
      .compact();

    String refresh_token = Jwts.builder()
      .setClaims(claims)
      .setSubject(user.getUsername())
      .setIssuedAt(createdDate)
      .setExpiration(refreshExpirationDate)
      .signWith(SignatureAlgorithm.HS256, "secret".getBytes())
      .compact();

    return Map.of(
      "access_token", accessToken,
      "refresh_token", refresh_token
    );

  }
  public Boolean validateToken(Claims claims, String username) {
    final String usernameToken = getUsernameFromToken(claims);
    return (usernameToken.equals(username) && !isTokenExpired(claims));
  }

  private Date calculateExpirationDate(Date createdDate, long multiplier) {
    return new Date(createdDate.getTime() + expiration * multiplier);
  }

  private UUID getIdFromToken(Claims claims) {
    return getClaimFromToken(claims, c -> UUID.fromString(c.getId()));
  }
}