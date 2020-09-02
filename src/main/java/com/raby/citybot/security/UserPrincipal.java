package com.raby.citybot.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.raby.citybot.repository.model.Role;
import com.raby.citybot.repository.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class UserPrincipal implements UserDetails {
    private Long id;
    private String name;
    private String surname;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;
    private Role role;
    private GrantedAuthority authorities;

    public UserPrincipal(Long id, String name, String email, String surname, String password, GrantedAuthority authorities, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.role = role;
    }

    public static UserPrincipal create(User user) {
        GrantedAuthority authorities = new SimpleGrantedAuthority(user.getRole().toString());
        return new UserPrincipal(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getSurname(),
                user.getPassword(),
                authorities,
                user.getRole()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return surname;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(authorities);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
