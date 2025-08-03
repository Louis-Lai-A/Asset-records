package com.homework.Asset.records.service;


import com.homework.Asset.records.dto.RegisterRequestDTO;
import com.homework.Asset.records.dto.UpdateUserProfileDTO;
import com.homework.Asset.records.model.User;
import com.homework.Asset.records.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(RegisterRequestDTO request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("使用者名稱已存在");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email 已存在");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .fullName(request.getFullName())
                .role(User.UserRole.USER) // 默認值移至此處
                .enabled(true)       // 默認值移至此處
                .build();

        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User updateProfile(Long userId, @Valid UpdateUserProfileDTO request) {
        User user = findById(userId);
        if (!request.getEmail().isEmpty()) {
            user.setEmail(request.getEmail());
        }
        if (!request.getFullName().isEmpty()) {
            user.setFullName(request.getFullName());
        }
        return userRepository.save(user);
    }
}