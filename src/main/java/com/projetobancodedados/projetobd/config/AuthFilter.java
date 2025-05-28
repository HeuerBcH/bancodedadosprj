package com.projetobancodedados.projetobd.config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthFilter implements Filter {

    // Rotas públicas (ajuste conforme necessário)
    private static final List<String> PUBLIC_PATHS = Arrays.asList(
        "/login", "/login.html", "/api/auth/check"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getRequestURI();

        // Permite acesso a rotas públicas e arquivos estáticos
        boolean isPublic = PUBLIC_PATHS.stream().anyMatch(path::startsWith)
            || path.startsWith("/css/") || path.startsWith("/js/") || path.startsWith("/images/")
            || path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".png") || path.endsWith(".ico") || path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".svg") || path.endsWith(".woff2") || path.endsWith(".html");

        if (isPublic) {
            chain.doFilter(request, response);
            return;
        }

        // Verifica se está logado
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            chain.doFilter(request, response);
        } else {
            // Se for requisição de API, retorna 401. Se for página, redireciona para login.
            if (path.startsWith("/api/") || path.endsWith(".json")) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                res.sendRedirect("/login.html");
            }
        }
    }
}
