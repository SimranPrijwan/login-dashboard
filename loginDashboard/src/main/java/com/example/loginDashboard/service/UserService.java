package com.example.loginDashboard.service;



import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.loginDashboard.entity.Role;
import com.example.loginDashboard.entity.User;
import com.example.loginDashboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserService {
	
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
	
    public String getHelloMessage() {
        return "Hello Sim";
    }
    
    public User registerUser(String username, String password, Role role) {
        Optional<User> existingUser = userRepository.findByUsername(username);

        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken!");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }
    public Optional<User> findByUsername(String username){
    	
    	return userRepository.findByUsername(username);
    }
}
