package com.ds.sqs.taxation.api.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class SecurityIdentityUtils {

    private static final String ORGANIZATION_ID_ATTR = "securityIdentity.organizationId";
    private static final String COMPANY_ID_ATTR = "securityIdentity.companyId";

    private SecurityIdentityUtils() {
    }

    public static Long getOrganizationId(HttpServletRequest request) {
        return toLong(request.getAttribute(ORGANIZATION_ID_ATTR));
    }

    public static Long getCompanyId(HttpServletRequest request) {
        return toLong(request.getAttribute(COMPANY_ID_ATTR));
    }

    public static Long getOrganizationId() {
        HttpServletRequest request = currentRequest();
        return request == null ? null : getOrganizationId(request);
    }

    public static Long getCompanyId() {
        HttpServletRequest request = currentRequest();
        return request == null ? null : getCompanyId(request);
    }

    private static HttpServletRequest currentRequest() {
        var attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes servletAttributes) {
            return servletAttributes.getRequest();
        }
        return null;
    }

    private static Long toLong(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Long longValue) {
            return longValue;
        }
        if (value instanceof Integer intValue) {
            return intValue.longValue();
        }
        if (value instanceof String str && !str.isBlank()) {
            return Long.parseLong(str);
        }
        return null;
    }
}
