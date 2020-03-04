package com.cedraz.exams.app.service.impl;

import com.cedraz.exams.app.exception.ApplicationException;
import com.cedraz.exams.app.exception.EntityType;
import com.cedraz.exams.app.exception.ExceptionType;
import com.cedraz.exams.app.model.PracticeExam;
import com.cedraz.exams.app.repository.PracticeExamRepository;
import com.cedraz.exams.app.service.PracticeExamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class PracticeExamServiceImpl implements PracticeExamService {

    @Autowired
    private PracticeExamRepository practiceExamRepository;

    @Override
    public List<PracticeExam> listAll() {
        return new ArrayList<>(practiceExamRepository.findAll());
    }

    @Override
    public List<PracticeExam> listAllByGrade(String grade) {
        List<PracticeExam> exams = new ArrayList<>(practiceExamRepository.findAllByGrade(grade));
        if(exams.size() == 0) throw exception(EntityType.PRACTICE_EXAM, ExceptionType.ENTITY_NOT_FOUND, grade);
        return exams;
    }

    @Override
    public PracticeExam listByStudent(Integer studentRegistration) {
        PracticeExam exam = practiceExamRepository.findByStudent_RegistrationNumber(studentRegistration);
        if(exam == null) throw exception(EntityType.PRACTICE_EXAM, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(studentRegistration));
        return exam;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ApplicationException.throwException(entityType, exceptionType, args);
    }
}
