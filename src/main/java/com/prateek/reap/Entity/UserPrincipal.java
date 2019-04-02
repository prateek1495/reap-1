package com.prateek.reap.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserPrincipal extends org.springframework.security.core.userdetails.User {

    private final User user;

    public UserPrincipal(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }

    public UserPrincipal(String email, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNotExpired, boolean accountNonLocked, List<SimpleGrantedAuthority> authorities, User user) {
        super(user.getEmail(), user.getPassword(),enabled, accountNonExpired, credentialsNotExpired, accountNonLocked, authorities);
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}