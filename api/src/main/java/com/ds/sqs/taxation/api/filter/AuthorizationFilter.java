package com.ds.sqs.taxation.api.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ds.sqs.taxation.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();

        if ("OPTIONS".equalsIgnoreCase(method)) {
            return true;
        }

        if (path.startsWith("/api/v1/provinces")) {
            return true;
        }

        return path.equals("/api/v1/accounts/register")
                || path.equals("/api/v1/accounts/login")
                || path.equals("/api/v1/accounts/forgot-password")
                || path.equals("/api/v1/accounts/reset-password");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            writeUnauthorized(response, "Missing or invalid Authorization header");
            return;
        }

        String token = authHeader.substring(BEARER_PREFIX.length());
        try {
            DecodedJWT jwt = jwtService.verify(token);

            request.setAttribute("securityIdentity.subject", jwt.getSubject());
            request.setAttribute("securityIdentity.username", jwt.getClaim("username").asString());
            request.setAttribute("securityIdentity.role", jwt.getClaim("role").asString());
            request.setAttribute("securityIdentity.organizationId", jwt.getClaim("organization_id").asLong());
            request.setAttribute("securityIdentity.companyId", jwt.getClaim("company_id").asLong());
            request.setAttribute("securityIdentity.tokenUse", jwt.getClaim("token_use").asString());

            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            writeUnauthorized(response, "Invalid or expired token");
        }
    }

    private void writeUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{\"error\":\"" + message + "\"}");
    }
}
