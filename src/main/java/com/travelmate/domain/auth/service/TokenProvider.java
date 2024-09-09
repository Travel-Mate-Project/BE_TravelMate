package com.travelmate.domain.auth.service;

import com.travelmate.commons.constants.CookieName;
import com.travelmate.domain.auth.dto.ClientUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TokenProvider implements InitializingBean {
    public static final String BEARER = "Bearer ";
    private static final String USERID = "userid";
    private static final String USERNAME = "username";
    private static final String AUTHORITY_KEY = "auth";
    private final long accessTokenExpires = 2 * 60 * 60 * 1000; // 2시간
    private final long refreshTokenExpires = 14 * 60 * 60 * 1000; // 14시간
    private final String SALT;
    private Key encodedKey;

    public TokenProvider(@Value("${token.secret.key}") String salt) {
        this.SALT = salt;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(SALT);
        this.encodedKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(Authentication authentication) {
        long now = System.currentTimeMillis();
        ClientUserDetails principal = (ClientUserDetails) authentication.getPrincipal();
        Map<String, Object> payload = new HashMap<>();
        payload.put(USERID, principal.getUserId());
        payload.put(USERNAME, principal.getUsername());
        final String authority =
                principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(","));
        payload.put(AUTHORITY_KEY, authority);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setClaims(payload)
                .signWith(SignatureAlgorithm.HS256, encodedKey)
                .setExpiration(new Date(now + accessTokenExpires))
                .compact();
    }

    public String generateRefreshToken(Authentication authentication) {
        long now = System.currentTimeMillis();
        ClientUserDetails principal = (ClientUserDetails) authentication.getPrincipal();
        Map<String, Object> payload = new HashMap<>();
        payload.put(USERID, principal.getUserId());
        payload.put(USERNAME, principal.getUsername());
        final String authority =
                principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(","));
        payload.put(AUTHORITY_KEY, authority);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setClaims(payload)
                .signWith(SignatureAlgorithm.HS256, encodedKey)
                .setExpiration(new Date(now + refreshTokenExpires))
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims =
                Jwts.parserBuilder().setSigningKey(encodedKey).build().parseClaimsJws(token).getBody();
        List<GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITY_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        String userid = getUserid(token);
        String username = getUsername(token);
        String pw = getPw(token);
        ClientUserDetails principal =
                new ClientUserDetails(userid, username, pw, authorities);
        return new UsernamePasswordAuthenticationToken(principal, authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(encodedKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException |
                 MalformedJwtException e) {

            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {

            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {

            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {

            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public ResponseCookie makeTokenCookie(String key, String token) {
        return ResponseCookie.from(key, token)
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .maxAge(
                        key.equals(CookieName.REFRESH_TOKEN.getCode())
                                ? refreshTokenExpires
                                : accessTokenExpires)
                .path("/")
                .build();
    }

    public ResponseCookie makeExpiredTokenCookie(String key, String token) {
        return ResponseCookie.from(key, token)
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .maxAge(0)
                .path("/")
                .build();
    }

    public String getUserid(String token) {
        String apiKey = getValue("userid", token);
        if (StringUtils.hasText(apiKey)) {
            return apiKey;
        }
        return "";
    }

    public String getUsername(String token) {
        String apiKey = getValue("username", token);
        if (StringUtils.hasText(apiKey)) {
            return apiKey;
        }
        return "";
    }

    public String getPw(String token) {
        String apiKey = getValue("pw", token);
        if (StringUtils.hasText(apiKey)) {
            return apiKey;
        }
        return "";
    }

    public String getValue(String key, String token) {
        Claims claims = getClaims(token);
        log.info("get value of claims {}", claims);

        Object value = claims.get(key);
        if (value != null) {
            return value.toString();
        }
        return "";
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(encodedKey).build().parseClaimsJws(token).getBody();
    }
}
