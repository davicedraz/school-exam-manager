package com.cedraz.sas.exams.app.repository;

import com.cedraz.sas.exams.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByRegistrationNumber(int registrationNumber);
    void deleteByRegistrationNumber(int registrationNumber);

}
