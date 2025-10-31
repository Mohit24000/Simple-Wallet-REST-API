package com.learnSpringBoot.SimpleWallet.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;

@Component
public class CorrelationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String id = request.getHeader("X-Correlation-ID");
        if (id == null || id.isBlank()) id = UUID.randomUUID().toString();
        response.setHeader("X-Correlation-ID", id);
        // also attach to request attributes if other components want it
        request.setAttribute("correlationId", id);
        return true;
    }
}
