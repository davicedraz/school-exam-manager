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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/")
    private ResponseEntity<StudentResponseDto> saveStudent(@RequestBody StudentDto student) {
        Student newStudent = studentService.saveOrUpdate(student);
        return new ResponseEntity<>(StudentResponseDto.toResponseDto(newStudent), HttpStatus.CREATED);
    }

    @GetMapping("/")
    private ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        List<StudentResponseDto> students = studentService.listAllStudents()
                .stream().map(StudentResponseDto::toResponseDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{registration}")
    private ResponseEntity<StudentResponseDto> getStudentByRegistration(@PathVariable("registration") int registrationNumber) {
        Student studentFound = studentService.getStudentByRegistration(registrationNumber);
        return new ResponseEntity<>(StudentResponseDto.toResponseDto(studentFound), HttpStatus.OK);
    }

    @DeleteMapping("/{registration}")
    private ResponseEntity deleteStudent(@PathVariable("registration") int registrationNumber) {
        studentService.deleteStudentByRegistration(registrationNumber);
        return new ResponseEntity(HttpStatus.OK);
    }

}
