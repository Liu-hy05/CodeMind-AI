package com.codemind.user.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JwtUtil {


    private final SecretKey secretKey;


    private final long expiration;


    public JwtUtil(
            @Value("${jwt.secret}")
            String secret,

            @Value("${jwt.expiration}")
            long expiration
    ){

        this.secretKey =
                Keys.hmacShaKeyFor(
                        secret.getBytes()
                );

        this.expiration = expiration;
    }


    public String createToken(
            Long userId,
            String username,
            String role
    ){

        return Jwts.builder()
                .subject(username)
                .claim("userId", userId)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + expiration
                        )
                )
                .signWith(secretKey)
                .compact();
    }

}