package com.example.social_media.service;

import com.example.social_media.dao.AccountRepository;
import com.example.social_media.security.AuthenticationFacade;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Autowired
    private AuthenticationFacade authenticationFacade;

    public JwtService(AuthenticationFacade theAuthenticationFacade) {
        this.authenticationFacade = theAuthenticationFacade;
    }

    private String secretKey = "4d31f324d8c48a79bf43b6c1d30799a7f54fc34f17ad1091684158fa84043999";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            // extraClaims dùng để thêm thông tin vào token
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
//        User user = .(userDetails.getUsername());
        if (authenticationFacade.getUser() != null){
            extraClaims.put("userId", authenticationFacade.getUser().getUserId());
            extraClaims.put("fullName", authenticationFacade.getUser().getFullName());
        }
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // expire after 1 day
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                // compact() để tạo ra chuỗi token
                .compact();

    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    private Key getSignInKey() {
        byte[] keysByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keysByte);
    }


}
