package com.adidas.cities.security;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    // Method to create JWT and send it to the client on the response header.
	static void addAuthentication(HttpServletResponse res, String username) {

        String token = Jwts.builder()
            .setSubject(username)
                
            // Expiration time of 1 minute. 
            .setExpiration(new Date(System.currentTimeMillis() + 60000))
            
            // Hash to sign the key
            .signWith(SignatureAlgorithm.HS512, "a1tY39m")
            .compact();

        //We add the token to the header
        res.addHeader("Authorization", "Bearer " + token);
    }

    // Method to validate token sent by the client
    static Authentication getAuthentication(HttpServletRequest request) {
        
        // We get the token from the request header.
        String token = request.getHeader("Authorization");
        
        // If there's a token we validate it. 
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey("a1tY39m")
                    .parseClaimsJws(token.replace("Bearer", "")) //This method validates.
                    .getBody()
                    .getSubject();

            // We don't require an auth for those request that are not /login,
            // so we can return a UsernamePasswordAuthenticationToken without password
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) :
                    null;
        }
        return null;
    }
}
