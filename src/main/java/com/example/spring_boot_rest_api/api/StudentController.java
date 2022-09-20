package com.example.spring_boot_rest_api.api;

import com.example.spring_boot_rest_api.dto.StudentRequest;
import com.example.spring_boot_rest_api.dto.StudentResponse;
import com.example.spring_boot_rest_api.dto.StudentResponseView;
import com.example.spring_boot_rest_api.entity.Student;
import com.example.spring_boot_rest_api.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
@Tag(name = "Student API",description = "ADMIN and MANAGER can create,update,delete students")
public class StudentController {

    private final StudentService service;

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    @Operation(description = "MANAGER can create a student")
    public StudentResponse create(@RequestBody StudentRequest student) {
       return service.addStudent2(student);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    @Operation(description = "MANAGER can create the student")
    public StudentResponse getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @PutMapping("/block/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(description = "ADMIN can block the student")
    public StudentResponse block(@PathVariable("id")Long id) {
        return service.block(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    @Operation(description = "MANAGER and ADMIN can update the student")
    public StudentResponse update(@PathVariable("id")Long id,@RequestBody StudentRequest studentRequest) {
        return service.update(id,studentRequest);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(description = "ADMIN can delete the student")
    public StudentResponse deleteById(@PathVariable("id") Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('MANAGER')")
    @Operation(description = "MANAGER can find all students")
    public List<Student> findAll() {
        return service.findAll();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(description = "ADMIN can search the student")
    public StudentResponseView getAllPagination(@RequestParam(value = "text",required = false)String text,
                                                @RequestParam int page,
                                                @RequestParam int size) {
        return service.getAllStudentsPagination(text,page,size);
    }
}
