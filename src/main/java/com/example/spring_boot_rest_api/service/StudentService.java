package com.example.spring_boot_rest_api.service;

import com.example.spring_boot_rest_api.dto.StudentRequest;
import com.example.spring_boot_rest_api.dto.StudentResponse;
import com.example.spring_boot_rest_api.dto.StudentResponseView;
import com.example.spring_boot_rest_api.entity.Student;
import com.example.spring_boot_rest_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public StudentResponse addStudent2(StudentRequest studentRequest) {
        Student student = createStudent(studentRequest);
        return response(repository.save(student));
    }

    public StudentResponseView getAllStudentsPagination(String text, int page, int size) {
        StudentResponseView view = new StudentResponseView();
        Pageable pageable = PageRequest.of(page-1,size);
        view.setStudentResponse(getStudents(search(text,pageable)));
        return view;
    }

    private List<Student> search(String name,Pageable pageable) {
        String text = name == null ? "" : name;
        return repository.searchByName(text.toUpperCase(),pageable);
    }

    public List<StudentResponse> getStudents(List<Student> students) {
        List<StudentResponse> responses = new ArrayList<>();
        for(Student student : students) {
            responses.add(getResponse(student));
        }
        return responses;
    }
    public Student createStudent(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setDeleted(student.isDeleted());
        student.setActive(student.isActive());
        student.setCreated(LocalDateTime.now());
        return student;
    }

    public StudentResponse response(Student student) {
        return getResponse(student);
    }

    public StudentResponse update(Long id,StudentRequest request) {
        Student student = repository.findById(id).get();
        Student student1 = updateStudent(student, request);
        repository.save(student1);
        return getResponse(student1);
    }

    public StudentResponse getById(Long id) {
        Student student = repository.findById(id).get();
        return getResponse(student);
    }

    public StudentResponse block(Long id) {
        Student student = repository.findById(id).get();
        student.setActive(false);
        repository.save(student);
        return getResponse(student);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public StudentResponse deleteById(Long id) {
        Student student = repository.findById(id).get();
        repository.delete(student);
        return getResponse(student);
    }

    public Student updateStudent(Student student, StudentRequest request) {
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setActive(student.isActive());
        student.setDeleted(student.isDeleted());
        student.setCreated(student.getCreated());
        return student;
    }

    public StudentResponse getResponse(Student student) {
        StudentResponse student1 = new StudentResponse();
        student1.setId(student.getId());
        student1.setName(student.getName());
        student1.setSurname(student.getSurname());
        student1.setAge(student.getAge());
        student1.setEmail(student.getEmail());
        student1.setActive(student.isActive());
        student1.setDeleted(student.isDeleted());
        student1.setCreated(student.getCreated());
        return student1;
    }
 }
