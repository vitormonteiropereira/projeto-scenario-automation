package com.scenarioautomation.demo.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String path = request.getRequestURI();

        // Rotas públicas: auth (login) e Swagger/OpenAPI
        if (path.startsWith("/auth/")
                || path.startsWith("/swagger-ui/")
                || path.startsWith("/v3/api-docs")) {
            chain.doFilter(request, response);
            return;
        }

        // Lê o header Authorization: Bearer <token>
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (jwtUtil.isValid(token)) {
                String username = jwtUtil.getUsername(token);

                // Cria Authentication simples (sem roles)
                var auth = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        AuthorityUtils.NO_AUTHORITIES
                );

                // Registra no SecurityContext para esta requisição
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // Segue a cadeia de filtros
        chain.doFilter(request, response);
    }
}
