package org.example.datingapp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.datingapp.Friendship.entities.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private final Key key;



    public JwtTokenUtil(@Value("${jwt.secret}") String secret) {
        System.out.println("Loaded JWT Secret: " + secret); // Debugging line
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

    public String generateToken(UserEntity userEntity) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(userEntity.getEmail())
                .claim("userId", userEntity.getUserId())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now +  24 * 60 * 60 * 1000)) // 1 day
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // Add methods to validate the token, extract username, etc.
}
