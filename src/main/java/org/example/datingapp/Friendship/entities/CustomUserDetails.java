package org.example.datingapp.Friendship.entities;

import org.example.datingapp.Friendship.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You can add roles or authorities here depending on your requirement
        return List.of(); // Return an empty list if you're not dealing with roles
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Or implement your logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Or implement your logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Or implement your logic
    }

    @Override
    public boolean isEnabled() {
        return true; // Or implement your logic
    }
}
