package com.travelmate.domain.user.handler.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ClientAccessDeniedHandler implements AccessDeniedHandler { // Spring Security에서 인가(Authorization) 실패가 발생했을 때 어떤 처리를 할지 정의하는 부분

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException)
            throws IOException {
        System.out.println("ClientAccessDeniedHandler");
        // 인가 실패 시 403 상태 코드 반환
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied : " + request);
    }
}
