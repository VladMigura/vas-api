package com.bsuir.vas.security.detail;

import com.bsuir.vas.entity.AdminEntity;
import com.bsuir.vas.exception.UnauthorizedException;
import com.bsuir.vas.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String adminName) throws UsernameNotFoundException {

        AdminEntity adminEntity = adminRepository.findOneByAdminName(adminName);

        if(adminEntity == null) throw new UnauthorizedException("Admin not found!");

        return new UserDetailsImpl(adminEntity);
    }
}
