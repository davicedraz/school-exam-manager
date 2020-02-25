package com.cedraz.sas.exams.app.repository;

import com.cedraz.sas.exams.app.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {}
