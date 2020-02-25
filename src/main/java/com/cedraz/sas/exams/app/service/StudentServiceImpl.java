package com.cedraz.sas.exams.app.service;

import com.cedraz.sas.exams.app.repository.StudentRepository;
import com.cedraz.sas.exams.app.dto.StudentDto;
import com.cedraz.sas.exams.app.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student saveOrUpdate(StudentDto student) {
        return studentRepository.save(student.toObject());
    }

    @Override
    public void delete(long id) {
        studentRepository.deleteById(id);
    }

}
