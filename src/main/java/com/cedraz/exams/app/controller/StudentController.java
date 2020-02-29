package com.cedraz.exams.app.controller;

import com.cedraz.exams.app.dto.StudentDto;
import com.cedraz.exams.app.dto.response.StudentResponseDto;
import com.cedraz.exams.app.model.Student;
import com.cedraz.exams.app.service.StudentService;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@Api(description = "Operations pertaining to student management")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    private ResponseEntity<StudentResponseDto> saveStudent(@RequestBody @Valid StudentDto student) {
        Student newStudent = studentService.saveOrUpdate(student);
        return new ResponseEntity<>(StudentResponseDto.toResponseDto(newStudent), HttpStatus.CREATED);
    }

    @GetMapping("/students")
    private ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        List<StudentResponseDto> students = studentService.listAllStudents()
                .stream().map(StudentResponseDto::toResponseDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/{registration}")
    private ResponseEntity<StudentResponseDto> getStudentByRegistration(@PathVariable("registration") int registrationNumber) {
        Student studentFound = studentService.getStudentByRegistration(registrationNumber);
        return new ResponseEntity<>(StudentResponseDto.toResponseDto(studentFound), HttpStatus.OK);
    }

    @DeleteMapping("/students/{registration}")
    private ResponseEntity deleteStudent(@PathVariable("registration") int registrationNumber) {
        studentService.deleteStudentByRegistration(registrationNumber);
        return new ResponseEntity(HttpStatus.OK);
    }

}
