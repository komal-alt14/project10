package com.janitri.backend.services;

import com.janitri.backend.models.Role;
import com.janitri.backend.models.User;
import com.janitri.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String email, String password, String role) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        try {
            user.setRole(Role.valueOf(role.toUpperCase())); // ✅ Handling role properly
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role. Must be ADMIN or USER");
        }

        user.setUsername(email.split("@")[0]);

        return userRepository.save(user);
    }

    public boolean loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
}
}