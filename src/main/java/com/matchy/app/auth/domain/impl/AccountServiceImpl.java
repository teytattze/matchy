package com.matchy.app.auth.domain.impl;

import com.matchy.app.auth.domain.AccountService;
import com.matchy.app.auth.domain.entity.AccountEntity;
import com.matchy.app.user.domain.UserRepository;
import com.matchy.app.user.domain.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = getUserByEmail(email);
        return AccountEntity.of(user.getEmail(), user.getPassword(), user.getRoles());
    }

    private UserEntity getUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository
            .findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
