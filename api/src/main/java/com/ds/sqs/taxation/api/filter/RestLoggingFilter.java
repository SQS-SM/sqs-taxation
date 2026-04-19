package com.ds.sqs.taxation.api.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RestLoggingFilter implements Filter {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain)
            throws IOException, ServletException {

        ContentCachingRequestWrapper requestWrapper =
                new ContentCachingRequestWrapper((HttpServletRequest) request);

        ContentCachingResponseWrapper responseWrapper =
                new ContentCachingResponseWrapper((HttpServletResponse) response);

        long start = System.currentTimeMillis();

        filterChain.doFilter(requestWrapper, responseWrapper);

        long duration = System.currentTimeMillis() - start;

        logRequestResponse(requestWrapper, responseWrapper, duration);

        responseWrapper.copyBodyToResponse();
    }

    private void logRequestResponse(
            ContentCachingRequestWrapper request,
            ContentCachingResponseWrapper response,
            long duration) throws IOException {

        Map<String, Object> logMap = new HashMap<>();
        // Collect SQL executed
        List<Map<String,Object>> sqlQueries = SqlQueryCollector.get();

        logMap.put("method", request.getMethod());
        logMap.put("uri", request.getRequestURI());
        logMap.put("status", response.getStatus());
        logMap.put("query", request.getQueryString());
        logMap.put("durationMs", duration);
        logMap.put("sqlQueries", sqlQueries.toString());

        logMap.put(
                "requestBody",
                new String(request.getContentAsByteArray(),
                        StandardCharsets.UTF_8));

        logMap.put(
                "responseBody",
                new String(response.getContentAsByteArray(),
                        StandardCharsets.UTF_8));

        log.info(mapper.writeValueAsString(logMap));
        if (duration > 2000) {
            log.warn("SLOW_API durationMs={}", duration);
        }
    }
}
