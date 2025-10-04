package io.app.service;

import io.app.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY="3zEYxnei5RPxxCVdg57NTtMbdXk6bnFnOKiw1taxdUC06fRdQybzqGQkV1Yvc8DROAqNOyTQDwKse09PX+gQag==";

    public String extractUsername(String token){
        return extractClaims(token,Claims::getSubject);
    }

    public boolean isTokenValid(String token){
        return !isTokenExpired(token);
    }

    public boolean isTokenValid(UserDetails userDetails,String token){
        String username=extractUsername(token);
        return userDetails.getUsername().equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return new Date().after(extractExpiration(token));
    }

    private Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }

    public Long getUserIdFromToken(String token){
        return extractClaims(token).get("id",Long.class);
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String token){
        String role=extractClaims(token).get("role",String.class);
        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(role);
        return List.of(simpleGrantedAuthority);
    }

    private <T> T extractClaims(String token, Function<Claims,T> resolver){
        Claims claims=extractClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(signInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(User user){
        HashMap<String,Object> extraClaims=new HashMap<>();
        extraClaims.put("id",user.getId());
        extraClaims.put("role",user.getRole().name());
        return generateToken(user.getEmail(),extraClaims);
    }

    private String generateToken(String subject, Map<String, Object> extraClaims) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000L * 60 * 60 * 24 * 365L * 5))
                .signWith(signInKey())
                .compact();
    }

    private SecretKey signInKey(){
        byte[] key= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }
}
