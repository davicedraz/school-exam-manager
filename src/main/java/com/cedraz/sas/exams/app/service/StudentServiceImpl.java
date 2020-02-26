package com.cedraz.sas.exams.app.service;

import com.cedraz.sas.exams.app.exception.ApplicationException;
import com.cedraz.sas.exams.app.exception.EntityType;
import com.cedraz.sas.exams.app.exception.ExceptionType;
import com.cedraz.sas.exams.app.repository.StudentRepository;
import com.cedraz.sas.exams.app.dto.StudentDto;
import com.cedraz.sas.exams.app.model.Student;

import static com.cedraz.sas.exams.app.exception.EntityType.STUDENT;
import static com.cedraz.sas.exams.app.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cedraz.sas.exams.app.exception.ExceptionType.ENTITY_NOT_FOUND;

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
    public Student saveOrUpdate(StudentDto newStudent) {
        if(!studentExists(newStudent.getRegistrationNumber())) {
            newStudent.setPassword(bCryptPasswordEncoder.encode(newStudent.getPassword()));
            return studentRepository.save(newStudent.toObject());
        } else {
            throw exception(STUDENT, DUPLICATE_ENTITY, String.valueOf(newStudent.getRegistrationNumber()));
        }
    }

    @Override
    public List<Student> listAllStudents() {
        List<Student> students = new ArrayList<>();
        students.addAll(studentRepository.findAll());
        return students;
    }

    @Override
    public Student getStudentByRegistration(int registrationNumber) {
        if(studentExists(registrationNumber)) {
            return studentRepository.findByRegistrationNumber(registrationNumber);
        } else {
            throw exception(STUDENT, ENTITY_NOT_FOUND, String.valueOf(registrationNumber));
        }
    }

    public void deleteStudentByRegistration(int registrationNumber) {
        if(studentExists(registrationNumber)) {
            studentRepository.deleteByRegistrationNumber(registrationNumber);
        } else {
            throw exception(STUDENT, ENTITY_NOT_FOUND, String.valueOf(registrationNumber));
        }
    }

    private boolean studentExists(int registrationNumber) {
        Student student = studentRepository.findByRegistrationNumber(registrationNumber);
        return student == null ? false : true;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ApplicationException.throwException(entityType, exceptionType, args);
    }

}
