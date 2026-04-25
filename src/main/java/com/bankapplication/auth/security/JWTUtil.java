package com.bankapplication.auth.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    private static final String SECRET =
      "mySecretKeymySecretKeymySecretKey";

    private final Key key =
      Keys.hmacShaKeyFor(
         SECRET.getBytes()
      );

    public String generateToken(
        String customerId){

        return Jwts.builder()
            .setSubject(customerId)
            .setIssuedAt(new Date())
            .setExpiration(
                new Date(
                    System.currentTimeMillis()
                    +900000
                )
            )
            .signWith(key)
            .compact();
    }


    public String extractCustomerId(
         String token){

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public boolean validateToken(
        String token){

        try{
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

            return true;

        } catch(Exception e){
            return false;
        }
    }
}
