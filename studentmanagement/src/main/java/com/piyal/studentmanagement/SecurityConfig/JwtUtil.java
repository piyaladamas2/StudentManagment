package com.piyal.studentmanagement.SecurityConfig;

import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
  private final String SECRET = "secret_key_123";

  public String generateToken(String username, String role) {
    return Jwts.builder()
        .setSubject(username)
        .claim("role", role)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hrs
        .signWith(SignatureAlgorithm.HS256, SECRET)
        .compact();
  }

  public Claims extractClaims(String token) {
    return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
  }

  public String extractUsername(String token) {
    return extractClaims(token).getSubject();
  }

  public String extractRole(String token) {
    return (String) extractClaims(token).get("role");
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    return extractUsername(token).equals(userDetails.getUsername());
  }
}
