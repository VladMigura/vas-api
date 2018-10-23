package com.bsuir.vas.utility;

import com.bsuir.vas.security.detail.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component(value = "securityUtility")
public class SecurityUtility {

    public boolean isAuthenticated() {

        return SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetailsImpl;
    }
}
