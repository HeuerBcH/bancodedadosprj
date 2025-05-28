package com.projetobancodedados.projetobd.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final List<String> PUBLIC_PATHS = Arrays.asList(
        "/login", "/login.html", "/api/auth/check"
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        boolean isPublic = PUBLIC_PATHS.stream().anyMatch(path::startsWith)
            || path.startsWith("/css/") || path.startsWith("/js/") || path.startsWith("/images/")
            || path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".png") || path.endsWith(".ico")
            || path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".svg") || path.endsWith(".woff2");

        if (isPublic) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            return true;
        } else {
            if (path.startsWith("/api/") || path.endsWith(".json")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                response.sendRedirect("/login.html");
            }
            return false;
        }
    }
}
