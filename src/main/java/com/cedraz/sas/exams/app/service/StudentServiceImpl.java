package com.cedraz.sas.exams.app.service;

import com.cedraz.sas.exams.app.exception.ApplicationException;
import com.cedraz.sas.exams.app.exception.EntityType;
import com.cedraz.sas.exams.app.exception.ExceptionType;

import com.cedraz.sas.exams.app.repository.StudentRepository;
import com.cedraz.sas.exams.app.dto.StudentDto;
import com.cedraz.sas.exams.app.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.cedraz.sas.exams.app.exception.EntityType.STUDENT;
import static com.cedraz.sas.exams.app.exception.ExceptionType.DUPLICATE_ENTITY;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Student> listAllStudents() {
        List<Student> students = new ArrayList<>();
        students.addAll(studentRepository.findAll());
        return students;
    }

    @Override
    public Student getStudentByRegistration(int registrationNumber) {
        return studentRepository.findByRegistrationNumber(registrationNumber);
    }

    @Override
    public Student saveOrUpdate(StudentDto newStudent) {
        Student student = studentRepository.findByRegistrationNumber(newStudent.getRegistrationNumber());
        if(student == null) {
            return studentRepository.save(newStudent.toObject());
        } else {
            throw exception(STUDENT, DUPLICATE_ENTITY, String.valueOf(newStudent.getRegistrationNumber()));
        }
    }

    @Override
    public Student changePassword(StudentDto student, String newPassword) {
        return null;
    }

    @Override
    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ApplicationException.throwException(entityType, exceptionType, args);
    }

}
