package com.gaf.login.domain.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

public class H2ConsoleFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.startsWith("http://localhost:8080/h2-console")) {
            filterChain.doFilter(request, response);
        } else {
            // Continue the filter chain for other requests
            filterChain.doFilter(request, response);
        }
    }
}

