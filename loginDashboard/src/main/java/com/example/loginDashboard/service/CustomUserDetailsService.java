package com.example.loginDashboard.service;


import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.loginDashboard.entity.User;
import com.example.loginDashboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username); // ✅ Use correct variable name

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOptional.get(); // ✅ Corrected variable name

        return org.springframework.security.core.userdetails.User // ✅ Use correct User class
                .withUsername(user.getUsername()) 
                .password(user.getPassword()) 
                .roles(user.getRole().name()) // Convert Role enum to String
                .build();
    }

}
