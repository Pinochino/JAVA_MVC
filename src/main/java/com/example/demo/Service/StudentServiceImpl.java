package com.example.demo.Service;

import com.example.demo.Dao.StudentRepository;
import com.example.demo.Entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student saveAndFlush(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, Student student) {
        Student s1 = getStudentById(id);

        s1.setFullname(student.getFullname());
        s1.setGmail(student.getGmail());
        s1.setPassword(student.getPassword());
        s1.setNumberphone(student.getNumberphone());

       return studentRepository.save(s1);
    }

    @Override
    @Transactional
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

}
