package com.bsuir.vas.security.provider;

import com.bsuir.vas.entity.AdminEntity;
import com.bsuir.vas.entity.TokenEntity;
import com.bsuir.vas.exception.UnauthorizedException;
import com.bsuir.vas.repository.TokenRepository;
import com.bsuir.vas.security.authentication.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    @Qualifier(value = "userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        String token = tokenAuthentication.getName();

        TokenEntity tokenEntity = tokenRepository.findOneByValue(token);

        if(tokenEntity == null) {
            throw new UnauthorizedException("Token not found!");
        }

        AdminEntity adminEntity = tokenEntity.getAdminEntity();
        UserDetails userDetails = userDetailsService.loadUserByUsername(adminEntity.getAdminName());

        tokenAuthentication.setUserDetails(userDetails);
        tokenAuthentication.setAuthenticated(true);

        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
