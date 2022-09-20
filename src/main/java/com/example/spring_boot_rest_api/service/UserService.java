package com.example.spring_boot_rest_api.service;

import com.example.spring_boot_rest_api.dto.UserRequest;
import com.example.spring_boot_rest_api.dto.UserResponse;
import com.example.spring_boot_rest_api.entity.User;
import com.example.spring_boot_rest_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse create(UserRequest request) {
        User user = mapToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return mapToResponse(userRepo.save(user));
    }

    public User mapToEntity(UserRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .isActive(true)
                .created(LocalDateTime.now())
                .build();
    }

    public UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId()).
                email(user.getEmail())
                .name(user.getName())
                .created(user.getCreated())
                .isActive(user.isActive())
                .build();

    }
}
