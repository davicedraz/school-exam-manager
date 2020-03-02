package com.cedraz.exams.app.controller;

import com.cedraz.exams.app.model.PracticeExam;
import com.cedraz.exams.app.service.PracticeExamService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(description = "Operations pertaining to Practice Exams")
public class PracticeExamController {

    @Autowired
    private PracticeExamService practiceExamService;

    @GetMapping("/exams")
    private ResponseEntity<List<PracticeExam>> getAllExams() {
        return new ResponseEntity<>(practiceExamService.listAll(), HttpStatus.OK);
    }

}
