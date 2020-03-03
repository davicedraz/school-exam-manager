package com.cedraz.exams.app.service.impl;

import com.cedraz.exams.app.exception.ApplicationException;
import com.cedraz.exams.app.exception.EntityType;
import com.cedraz.exams.app.exception.ExceptionType;
import com.cedraz.exams.app.model.Student;
import com.cedraz.exams.app.repository.StudentRepository;
import com.cedraz.exams.app.dto.StudentDto;

import com.cedraz.exams.app.service.StudentService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Student saveNew(StudentDto newStudent) {
        if(!studentExists(newStudent.getRegistrationNumber())) {
            newStudent.setPassword(bCryptPasswordEncoder.encode(newStudent.getPassword()));
            return studentRepository.save(newStudent.toObject());
        } else {
            throw exception(EntityType.STUDENT, ExceptionType.DUPLICATE_ENTITY, String.valueOf(newStudent.getRegistrationNumber()));
        }
    }

    @Override
    public List<Student> listAllStudents() {
        List<Student> students = new ArrayList<>(studentRepository.findAll());
        return students;
    }

    @Override
    public Student getStudentByRegistration(int registrationNumber) {
        if(studentExists(registrationNumber)) {
            return studentRepository.findByRegistrationNumber(registrationNumber);
        } else {
            throw exception(EntityType.STUDENT, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(registrationNumber));
        }
    }

    public void deleteStudentByRegistration(int registrationNumber) {
        if(studentExists(registrationNumber)) {
            studentRepository.deleteByRegistrationNumber(registrationNumber);
        } else {
            throw exception(EntityType.STUDENT, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(registrationNumber));
        }
    }

    private boolean studentExists(int registrationNumber) {
        Student student = studentRepository.findByRegistrationNumber(registrationNumber);
        return student != null;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ApplicationException.throwException(entityType, exceptionType, args);
    }

}
