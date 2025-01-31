package com.carlos.witProject.MDCProp;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@WebFilter("/*")
public class RequestResponseFilter extends OncePerRequestFilter {

    public static final String REQUEST_ID_HEADER = "Request-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Generate a unique UUID for the request
        String requestId = UUID.randomUUID().toString();

        // If the incoming request already has an X-Request-ID, use it
        String existingRequestId = request.getHeader(REQUEST_ID_HEADER);
        if (existingRequestId != null) {
            requestId = existingRequestId;
        }

        // Set the request ID in MDC for logging purposes
        MDC.put(REQUEST_ID_HEADER, requestId);

        // Set the X-Request-ID in the response headers
        response.setHeader(REQUEST_ID_HEADER, requestId);
        filterChain.doFilter(request, response);

        MDC.clear();
    }
}
