package com.cedraz.exams.app.repository;

import com.cedraz.exams.app.model.PracticeExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PracticeExamRepository extends JpaRepository<PracticeExam, Long> {

    List<PracticeExam> findAllByGrade(String grade);
    PracticeExam findByStudent_RegistrationNumber(Integer registrationNumber);
    PracticeExam findByStudent_Id(long studentId);
    List<PracticeExam> findAllByOrderByTotalScoreDesc();

}