package com.matchy.app.auth.domain.entity;

import com.matchy.app.auth.domain.BaseAccountEntity;
import com.matchy.app.user.domain.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AccountEntity implements BaseAccountEntity {
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public AccountEntity(String username, String password, Collection<UserRole> roles) {
        this.username = username;
        this.password = password;
        this.authorities = roles.stream().map(Authority::from).toList();
    }

    public static AccountEntity of(String username, String password, Collection<UserRole> roles) {
        return new AccountEntity(username, password, roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
