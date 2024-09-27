package com.travelmate.domain.user.handler.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ClientAuthenticationEntryPoint implements AuthenticationEntryPoint { // 인증되지 않은 사용자가 보호된 리소스에 접근하려 할 때 처리하는 동작을 정의하는 것

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException, ServletException {
        System.out.println("ClientAuthenticationEntryPoint");
        // 인증되지 않은 요청이 발생했을 때 401 상태 코드를 반환
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication required");
    }
}
