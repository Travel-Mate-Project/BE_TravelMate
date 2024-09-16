package com.travelmate.domain.auth.filter;

import com.travelmate.commons.exception.ErrorCode;
import com.travelmate.commons.service.port.SystemHolder;
import com.travelmate.domain.auth.domain.TokenType;
import com.travelmate.domain.auth.service.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final SystemHolder systemHolder;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Optional<String> maybeAccessToken = resolveCookieValue(request, HttpHeaders.AUTHORIZATION);
        try {
            if (maybeAccessToken.isPresent()
                    && StringUtils.hasText(maybeAccessToken.get())
                    && tokenProvider.validateToken(maybeAccessToken.get())) {
                Authentication authentication = tokenProvider.getAuthentication(maybeAccessToken.get());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                log.debug("유효한 ACCESS 토큰이 존재하지 않습니다.");
                checkRefreshToken(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("exception", e);
        }

        filterChain.doFilter(request, response);
    }

    private void checkRefreshToken(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        Optional<String> refreshTokenOptional = resolveCookieValue(request, TokenType.REFRESH.getKey());

        if (refreshTokenOptional.isEmpty()
                || !StringUtils.hasText(refreshTokenOptional.get())
                || !tokenProvider.validateToken(refreshTokenOptional.get())) {
            throw new BadCredentialsException(ErrorCode.REFRESH_TOKEN_NOT_VALID.getMessage());
        }

        String refreshToken = refreshTokenOptional.get();
        Authentication authentication = tokenProvider.getAuthentication(refreshToken);
        String accessToken =
                tokenProvider.generateToken(TokenType.ACCESS, null, systemHolder); //TODO: null대신 user 넣어야하는지 보기
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.setHeader(HttpHeaders.AUTHORIZATION, TokenProvider.BEARER + accessToken);
        response.setHeader(TokenType.REFRESH.getKey(), TokenProvider.BEARER + refreshToken);
    }

    private Optional<String> resolveCookieValue(HttpServletRequest request, String key) {
        return Optional.ofNullable(request.getHeader(key))
                .filter(token -> token.startsWith(TokenProvider.BEARER))
                .map(refreshToken -> refreshToken.replace(TokenProvider.BEARER, ""));
    }
}
