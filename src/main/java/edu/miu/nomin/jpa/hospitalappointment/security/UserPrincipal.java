package edu.miu.nomin.jpa.hospitalappointment.security;

import edu.miu.nomin.jpa.hospitalappointment.credentials.CredentialsModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {
    private final CredentialsModel credentialsModel;
    public UserPrincipal(CredentialsModel credentialsModel) {
        this.credentialsModel = credentialsModel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.credentialsModel.getRole()));
    }

    @Override
    public String getPassword() {
        return this.credentialsModel.getPassword();
    }

    @Override
    public String getUsername() {
        return this.credentialsModel.getUsername();
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
}
