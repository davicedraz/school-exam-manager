package com.cedraz.sas.exams.app.controller;

import com.cedraz.sas.exams.app.dto.StudentDto;
import com.cedraz.sas.exams.app.dto.response.StudentResponseDto;
import com.cedraz.sas.exams.app.model.Student;
import com.cedraz.sas.exams.app.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    private List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    private Student getStudent(@PathVariable("id") long id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    private void deleteStudent(@PathVariable("id") long id) {
        studentService.delete(id);
    }

    @PostMapping("/")
    private ResponseEntity<StudentResponseDto> saveStudant(@RequestBody StudentDto student) {
        Student newStudent = studentService.saveOrUpdate(student);
        return new ResponseEntity<>(StudentResponseDto.toDto(newStudent), HttpStatus.CREATED);
    }

}
