package com.joaodinizaraujo.secretsantapicker.api.service;

import com.joaodinizaraujo.secretsantapicker.api.exception.RegisterNotFoundException;
import com.joaodinizaraujo.secretsantapicker.api.model.User;
import com.joaodinizaraujo.secretsantapicker.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getByEmail(String email) throws IllegalArgumentException {
        return userRepository.findById(email).orElseThrow(() -> {
            throw new RegisterNotFoundException("No user for this email.");
        });
    }

    public User insert(User user) {
        try {
            getByEmail(user.getEmail());
            throw new IllegalArgumentException("User with this email already exists.");
        } catch (IllegalArgumentException ignored) {
        }

        user.setName(user.getName().toUpperCase().strip());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }
}
