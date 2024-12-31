package com.ps.common;

import com.ps.common.utils.validator.RoleRequired;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class RoleValidationAspect {

    private static final String ROLES_HEADER = "X-Roles";

    private final HttpServletRequest request;

    public RoleValidationAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Before("@annotation(roleRequired)")
    public void checkRole(RoleRequired roleRequired) {
        String rolesHeader = request.getHeader(ROLES_HEADER);
        if (rolesHeader == null) {
            throw new SecurityException("You do not have permission to access this resource");
        }

        List<String> userRoles = Arrays.asList(rolesHeader.split(","));
        for (String requiredRole : roleRequired.value()) {
            if (userRoles.contains(requiredRole)) {
                return;
            }
        }

        throw new SecurityException("You do not have permission to access this resource");
    }
}
