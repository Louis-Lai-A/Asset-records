package com.homework.Asset.records.controller;

import com.homework.Asset.records.dto.UpdateUserProfileDTO;
import com.homework.Asset.records.dto.UserProfileDTO;
import com.homework.Asset.records.model.User;
import com.homework.Asset.records.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/users") // 改為複數形式
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserProfileDTO> getCurrentUserProfile(Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            UserProfileDTO profile = UserProfileDTO.fromUser(user);
            log.info("Fetched profile for user: {}", username);
            return ResponseEntity.ok(profile);
        } catch (RuntimeException e) {
            log.error("Error fetching user profile: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/profile/{userId}")
    @PreAuthorize("hasPermission(#userId, 'User', 'READ')")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Long userId) {
        try {
            User user = userService.findById(userId);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            UserProfileDTO profile = UserProfileDTO.fromUser(user);
            log.info("Fetched profile for user ID: {}", userId);
            return ResponseEntity.ok(profile);
        } catch (RuntimeException e) {
            log.error("Error fetching user profile for ID {}: {}", userId, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/profile/{userId}")
    @PreAuthorize("hasPermission(#userId, 'User', 'WRITE')")
    public ResponseEntity<UserProfileDTO> updateUserProfile(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserProfileDTO request) {
        try {
            User updatedUser = userService.updateProfile(userId, request);
            if (updatedUser == null) {
                throw new RuntimeException("User update failed");
            }
            UserProfileDTO profile = UserProfileDTO.fromUser(updatedUser);
            log.info("Updated profile for user ID: {}", userId);
            return ResponseEntity.ok(profile);
        } catch (RuntimeException e) {
            log.error("Error updating user profile for ID {}: {}", userId, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserProfileDTO>> getAllUsers() {
        try {
            List<User> users = userService.findAll();
            log.info("Fetched all users: {}", users.size());
            List<UserProfileDTO> profiles = users.stream()
                    .map(UserProfileDTO::fromUser)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(profiles);
        } catch (RuntimeException e) {
            log.error("Error fetching all users: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}