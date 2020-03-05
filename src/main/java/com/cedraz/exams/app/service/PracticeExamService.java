package com.cedraz.exams.app.service;

import com.cedraz.exams.app.model.PracticeExam;
import com.cedraz.exams.app.model.Student;

import java.util.List;

public interface PracticeExamService {

    List<PracticeExam> listAll();
    List<PracticeExam> listAllByGrade(String grade);
    PracticeExam listExamForStudent(Integer studentRegistration);
    PracticeExam evaluateIndividualExam(Integer studentRegistration);
    List<PracticeExam> evaluateBestStudents();

}
