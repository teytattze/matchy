package com.matchy.app.auth.domain.entity;

import com.matchy.app.user.domain.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
    private final String authority;

    private Authority(String authority) {
        this.authority = authority;
    }

    public static Authority from(UserRole role) {
        return new Authority(role.name());
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
