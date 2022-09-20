package com.example.spring_boot_rest_api.repository;

import com.example.spring_boot_rest_api.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where upper(s.name) like concat('%',:text,'%') " +
            "or upper(s.email) like concat('%',:text,'%') or upper(s.surname) like concat('%',:text,'%')")
    List<Student> searchByName(@Param("text")String text, Pageable pageable);
}