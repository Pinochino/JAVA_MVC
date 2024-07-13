package com.example.demo.Controller;

import com.example.demo.Entity.Student;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CRUD DATA
    @GetMapping("/list")
    public String listAllStudent(Model model){
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "/crud/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "/crud/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("student") Student student){
        if (student.getId() == null) {
            studentService.save(student);
        } else {
            studentService.updateStudent(student.getId(), student);
        }
        return "redirect:/api/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Long id, Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "/crud/form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        studentService.deleteStudentById(id);
        return "redirect:/api/list";
    }

    // SECURITY
    @GetMapping("/home")
    public String showHomePage(){
        return "/security/home";
    }


   @GetMapping("/students")
   public List<Student> getAllStudents(){
       return studentService.getAllStudents();
   }

   @GetMapping("/students/{id}")
   public ResponseEntity<Student> getStudentById(@PathVariable Long id){
       Student student = studentService.getStudentById(id);
       if (student != null){
           return ResponseEntity.ok(student);
       }
       return ResponseEntity.notFound().build();
   }

   @PostMapping("/students")
   public ResponseEntity<Student> updateStudent(@RequestBody  Student student){
       Student Existingstudent = studentService.saveAndFlush(student);
       if (Existingstudent != null){
           Existingstudent.setFullname(student.getFullname());
           Existingstudent.setGmail(student.getGmail());
           Existingstudent.setPassword(student.getPassword());
           Existingstudent.setNumberphone(student.getNumberphone());
           studentService.saveAndFlush(Existingstudent);
           return ResponseEntity.ok(Existingstudent);
       } else {
           return ResponseEntity.notFound().build();
       }
   }

   @PutMapping("/students/{id}")
   public ResponseEntity<Student> updateStudentById(@PathVariable Long id, @RequestBody Student student){
       if (id == null) {
           return ResponseEntity.badRequest().build();
       }

       Optional<Student> updateStudent = Optional.ofNullable(studentService.updateStudent(id, student));
       return updateStudent.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
   }

   @DeleteMapping("/students/{id}")
   public  ResponseEntity<Void> deleteStudentById(@PathVariable Long id){
       Student Existingstudent =  studentService.getStudentById(id);
       if (Existingstudent != null){
           studentService.getStudentById(id);

           List<Student> studentList = studentService.getAllStudents();
           for (Student s:studentList) {
               if (s.getId() > id){
                   s.setId(s.getId() - 1);
                   studentService.save(s);
               }
           }
           return ResponseEntity.ok().build();
       } else {
           return ResponseEntity.notFound().build();
       }
   }



}