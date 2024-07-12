package com.example.demo.Service;

import com.example.demo.Entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student save(Student student);

    Student saveAndFlush(Student student);

    Student updateStudent(Long id, Student student);

    void deleteStudentById(Long id);
}