package com.example.spring_boot_rest_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private int age;
    private boolean isDeleted = false;
    private boolean isActive = true;
    private LocalDateTime created;
}
