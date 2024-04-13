package org.example.datingapp.Login.services;

import org.example.datingapp.Friendship.entities.UserEntity;
import org.example.datingapp.Friendship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService  {
    @Autowired
    private UserRepository userRepository;

    public UserEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found: " + email));
        return user;
    }
}
