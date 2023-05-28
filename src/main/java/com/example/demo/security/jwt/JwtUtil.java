package com.example.demo.security.jwt;

import com.example.demo.entity.security.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.UserSecurity;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${ecommerce.app.jwtSecret}")
    private String jwtSecret;

    @Value("${ecommerce.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Autowired
    private UserRepository userRepository;

    private static final String AUTH = "auth";

    public String generateToken(Authentication authentication) {
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        String authority = authentication.getAuthorities().stream().map(grandauthority -> grandauthority.getAuthority()).collect(Collectors.joining());
        User user =  userSecurity.getUser();
        return Jwts.builder()
                .setSubject((user.getUserName()))
                .claim(AUTH, authority)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Authentication getAuthentication(String token) throws UsernameNotFoundException {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        Collection<? extends GrantedAuthority> authorities = Arrays.asList((GrantedAuthority) new SimpleGrantedAuthority(claims.get(AUTH).toString()));
        User user = userRepository.findByUserName(claims.getSubject()).orElseThrow(() -> new UsernameNotFoundException("UserName not found from jwt"));
        UserSecurity userSecurity = new UserSecurity(user);
        return new UsernamePasswordAuthenticationToken(userSecurity, token, authorities);

    }


    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
