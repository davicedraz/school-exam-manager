package com.cedraz.exams.app.service;

import com.cedraz.exams.app.model.PracticeExam;

import java.util.List;

public interface PracticeExamService {

    List<PracticeExam> listAll();
    List<PracticeExam> listAllByGrade(String grade);
    PracticeExam listByStudent(Integer studentRegistration);

}
