package com.example.spring_boot_rest_api.api;

import com.example.spring_boot_rest_api.dto.UserRequest;
import com.example.spring_boot_rest_api.dto.UserResponse;
import com.example.spring_boot_rest_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public UserResponse create(@RequestBody UserRequest request) {
        return service.create(request);
    }
}
