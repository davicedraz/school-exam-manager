package com.cedraz.sas.exams.app.service;

import com.cedraz.sas.exams.app.dto.StudentDto;
import com.cedraz.sas.exams.app.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> listAllStudents();
    Student getStudentByRegistration(int registrationNumber);
    Student saveOrUpdate(StudentDto studant);
    void deleteStudentByRegistration(int registrationNumber);

}
