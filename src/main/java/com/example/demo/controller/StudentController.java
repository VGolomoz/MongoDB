package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentController {

    StudentService studentService;

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/get/{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/get/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/getByName/{name}")
    public List<Student> getStudentsByName(@PathVariable String name) {
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/getByNameAndMail")
    public List<Student> getStudentsByNameAndMail(@RequestParam String name,
                                           @RequestParam String email) {
        return studentService.getStudentsByNameAndMail(name, email);
    }

    @GetMapping("/getByNameOrMail")
    public List<Student> getStudentsByNameOrMail(@RequestParam String name,
                                                  @RequestParam String email) {
        return studentService.getStudentsByNameOrMail(name, email);
    }

    @GetMapping("/get/all/pagination")
    public List<Student> getAllWithPagination(@RequestParam int pageSize,
                                                 @RequestParam int pageNo) {
        return studentService.getAllWithPagination(pageSize, pageNo);
    }

    @GetMapping("/get/byDepartmentName")
    public List<Student> byDepartmentName(@RequestParam String deptName) {
        return studentService.byDepartmentName(deptName);
    }

    @GetMapping("/get/bySubjectName")
    public List<Student> bySubjectName(@RequestParam String subjectName) {
        return studentService.bySubjectName(subjectName);
    }

    @GetMapping("/get/emailLike")
    public List<Student> emailLike(@RequestParam String email) {
        return studentService.emailLike(email);
    }

    @GetMapping("/get/nameStartsWith")
    public List<Student> nameStartsWith(@RequestParam String name) {
        return studentService.nameStartsWith(name);
    }

    @GetMapping("/get/byDepartmentId")
    public List<Student> byDepartmentId(@RequestParam String deptId) {
        return studentService.byDepartmentId(deptId);
    }
}
