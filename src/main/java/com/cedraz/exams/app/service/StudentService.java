package com.cedraz.exams.app.service;

import com.cedraz.exams.app.dto.StudentDto;
import com.cedraz.exams.app.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> listAllStudents();
    Student getStudentByRegistration(int registrationNumber);
    Student saveNew(StudentDto student);
    void deleteStudentByRegistration(int registrationNumber);

}
