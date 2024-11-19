package com.joaodinizaraujo.secretsantapicker.api.service;

import com.joaodinizaraujo.secretsantapicker.api.model.User;
import com.joaodinizaraujo.secretsantapicker.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getByEmail(String email) {
        return userRepository.findById(email).orElse(null);
    }

    public User insert(User user) {
        user.setName(user.getName().toUpperCase().strip());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }
}
