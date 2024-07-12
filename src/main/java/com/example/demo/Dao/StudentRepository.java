package com.example.demo.Dao;

import com.example.demo.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsStudentByFullname(String fullname);
}