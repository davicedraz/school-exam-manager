package com.cedraz.exams.app.service.impl;

import com.cedraz.exams.app.exception.ApplicationException;
import com.cedraz.exams.app.exception.EntityType;
import com.cedraz.exams.app.exception.ExceptionType;
import com.cedraz.exams.app.model.PracticeExam;
import com.cedraz.exams.app.model.Student;
import com.cedraz.exams.app.repository.PracticeExamRepository;
import com.cedraz.exams.app.repository.StudentRepository;
import com.cedraz.exams.app.service.PracticeExamService;

import com.cedraz.exams.app.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

@Component
@Transactional
public class PracticeExamServiceImpl implements PracticeExamService {

    @Autowired
    private TestService testService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PracticeExamRepository practiceExamRepository;

    @Override
    public List<PracticeExam> listAll() {
        return new ArrayList<>(practiceExamRepository.findAll());
    }

    @Override
    public List<PracticeExam> listAllByGrade(String grade) {
        List<PracticeExam> exams = new ArrayList<>(practiceExamRepository.findAllByGrade(grade));
        if(exams.size() == 0)
            throw exception(EntityType.EXAM, ExceptionType.ENTITY_NOT_FOUND, grade);
        return exams;
    }

    @Override
    public PracticeExam listExamForStudent(Integer studentRegistration) {
        PracticeExam exam = practiceExamRepository.findByStudent_RegistrationNumber(studentRegistration);
        if(exam == null)
            throw exception(EntityType.EXAM, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(studentRegistration));
        return exam;
    }

    @Override
    public PracticeExam evaluateIndividualExam(Integer studentRegistration) {
        PracticeExam exam = practiceExamRepository.findByStudent_RegistrationNumber(studentRegistration);
        if(exam == null)
            throw exception(EntityType.EXAM, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(studentRegistration));

        List<Integer> testScores = exam.getTests().stream()
                .map(test -> testService.calculateScore(test))
                .collect(Collectors.toList());

        Integer totalScore = testScores.stream().reduce(0, Integer::sum);
        exam.setTotalScore(totalScore);

        practiceExamRepository.save(exam);
        return exam;
    }

    @Override
    public List<PracticeExam> evaluateBestStudents() {
        List<Student> students = studentRepository.findAll();
        students.forEach(student -> evaluateIndividualExam(student.getRegistrationNumber()));
        return practiceExamRepository.findAllByOrderByTotalScoreDesc();
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ApplicationException.throwException(entityType, exceptionType, args);
    }

}
