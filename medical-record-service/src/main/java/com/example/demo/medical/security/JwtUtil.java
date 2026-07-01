//package com.example.demo.doctor.security;
//
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtil {
////	 private static final String SECRET = "HospitalManagementSystemSecretKey2024!!";
//	// Change this line:
//	 private static final String SECRET = "HospitalManagementSystemSecretKey2024!!HospitalManagementSystemSecretKey2024!!";
//	    private static final long EXPIRATION = 86400000; // 24 hours
//
//	    private Key getSigningKey() {
//	        return Keys.hmacShaKeyFor(SECRET.getBytes());
//	    }
//
//	    // generate token
//	    public String generateToken(String email) {
//	        return Jwts.builder()
//	                .setSubject(email)
//	                .setIssuedAt(new Date())
//	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
//	                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//	                .compact();
//	    }
//
//	    // extract email from token
//	    public String extractEmail(String token) {
//	        return Jwts.parserBuilder()
//	                .setSigningKey(getSigningKey())
//	                .build()
//	                .parseClaimsJws(token)
//	                .getBody()
//	                .getSubject();
//	    }
//	    // validate token
//	    public boolean validateToken(String token) {
//	        try {
//	            Jwts.parserBuilder()
//	                    .setSigningKey(getSigningKey())
//	                    .build()
//	                    .parseClaimsJws(token);
//	            return true;
//	        } catch (JwtException e) {
//	            return false;
//	        }
//	    }
//}
//
//
//
//


package com.example.demo.medical.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "HospitalManagementSystemSecretKey2024!!";
    private static final long EXPIRATION = 86400000;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        System.out.println("=== Extracting email from token ===");
        String email = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        System.out.println("Extracted email: " + email);
        return email;
    }

    public boolean validateToken(String token) {
        System.out.println("=== Validating token ===");
        System.out.println("Token: " + token);
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            System.out.println("Token is VALID!");
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Token is INVALID! Reason: " + e.getMessage());
            return false;
        }
    }
}