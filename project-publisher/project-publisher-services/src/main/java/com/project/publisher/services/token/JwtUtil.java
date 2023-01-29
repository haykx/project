package com.project.publisher.services.token;

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

  public PublisherPrincipal getPrincipalFromToken(final String token){
    final Claims claims = getAllClaimsFromToken(token);
    if(this.isTokenExpired(claims)){
      throw new RuntimeException();
    }
    final UUID id = UUID.fromString(this.getIdFromToken(claims));
    final String username = this.getUsernameFromToken(claims);
    final Set<SimpleGrantedAuthority> permissions = this.getPermissions(claims);
    return new PublisherPrincipal(id, username, null, permissions);
  }

  private Set<SimpleGrantedAuthority> getPermissions(final Claims claims) {
    return this.getClaimFromToken(claims, c -> (List<String>) c.get("permissions")).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
  }

  private String getUsernameFromToken(final Claims claims) {
    return getClaimFromToken(claims, Claims::getSubject);
  }

  private Date getExpirationDateFromToken(final Claims claims) {
    return getClaimFromToken(claims, Claims::getExpiration);
  }

  private  <T> T getClaimFromToken(final Claims claims, final Function<Claims, T> claimsResolver) {
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(final String token) {
    return Jwts.parser()
      .setSigningKey("secret".getBytes())
      .parseClaimsJws(token)
      .getBody();
  }

  private Boolean isTokenExpired(final Claims claims) {
    final Date expiration = getExpirationDateFromToken(claims);
    return expiration.before(new Date());
  }

  private String getIdFromToken(final Claims claims) {
    return getClaimFromToken(claims, Claims::getId);
  }
}