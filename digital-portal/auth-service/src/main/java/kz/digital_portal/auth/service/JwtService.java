package kz.digital_portal.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import kz.digital_portal.auth.model.User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private static final String SECRET = "your-very-strong-secret-key-32-characters-long!!";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        claims.put("userId", user.getId());
        if (user.getUniversityId() != null) {
            claims.put("universityId", user.getUniversityId());
        }

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 часа
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
