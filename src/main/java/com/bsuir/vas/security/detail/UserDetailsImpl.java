package com.bsuir.vas.security.detail;

import com.bsuir.vas.entity.AdminEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private AdminEntity adminEntity;

    public UserDetailsImpl(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(adminEntity.getAdminName()));
    }

    @Override
    public String getPassword() {
        return adminEntity.getHashPassword();
    }

    @Override
    public String getUsername() {
        return adminEntity.getAdminName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getId() {
        return adminEntity.getId();
    }
}
