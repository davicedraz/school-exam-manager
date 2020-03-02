package com.cedraz.exams.app.service.impl;

import com.cedraz.exams.app.model.PracticeExam;
import com.cedraz.exams.app.repository.PracticeExamRepository;
import com.cedraz.exams.app.service.PracticeExamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PracticeExamServiceImpl implements PracticeExamService {

    @Autowired
    private PracticeExamRepository practiceExamRepository;

    @Override
    public List<PracticeExam> listAll() {
        List<PracticeExam> exams = new ArrayList<>(practiceExamRepository.findAll());
        return exams;
    }

    @Override
    public List<PracticeExam> listAllByGrade() {
        return null;
    }

    @Override
    public PracticeExam listByStudent() {
        return null;
    }
}
