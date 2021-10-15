package org.sid.auth;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.sid.entities.Authenticate;

@Log4j2
public class AuthenticateDetails implements UserDetails {

    private final Set<? extends GrantedAuthority> grantedAuthorities;

    private final String username;
    private final String password;

    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    private final Authenticate authenticate;


    public AuthenticateDetails(Authenticate authenticate) {
        this.grantedAuthorities = Arrays.stream( authenticate.getRoles().split(",") )
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet());

        this.username = authenticate.getUsername();
        this.password = authenticate.getPassword();

        this.isAccountNonExpired = authenticate.isAccountNonExpired();
        this.isAccountNonLocked = authenticate.isAccountNonLocked();
        this.isCredentialsNonExpired = authenticate.isCredentialsNonExpired();
        this.isEnabled = authenticate.isEnabled();

        this.authenticate = authenticate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Authenticate getAuthenticate() {
        return authenticate;
    }


}
