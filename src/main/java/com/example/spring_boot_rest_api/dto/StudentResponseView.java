package com.example.spring_boot_rest_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentResponseView {

    private List<StudentResponse> studentResponse;

}
