package com.cedraz.exams.app.controller;

import com.cedraz.exams.app.dto.response.ExamResultResponseDto;
import com.cedraz.exams.app.dto.response.PracticeExamResponseDto;
import com.cedraz.exams.app.model.PracticeExam;
import com.cedraz.exams.app.service.PracticeExamService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.Api;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@Api(description = "Operations pertaining to Practice Exams")
public class PracticeExamController {

    @Autowired
    private PracticeExamService practiceExamService;

    @GetMapping("/exams")
    private ResponseEntity<List<PracticeExamResponseDto>> getAllExams() {
        List<PracticeExamResponseDto> exams = practiceExamService.listAll()
                .stream().map(PracticeExamResponseDto::toResponseDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @GetMapping("/exams/{grade}")
    private ResponseEntity<List<PracticeExamResponseDto>> getAllExamsByGrade(@PathVariable("grade") String grade) {
        List<PracticeExamResponseDto> exams = practiceExamService.listAllByGrade(grade)
                .stream().map(PracticeExamResponseDto::toResponseDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @GetMapping("/exam/student/{studentRegistration}")
    private ResponseEntity<PracticeExamResponseDto> getAllExamsByGrade(@PathVariable("studentRegistration") Integer studentRegistration) {
        PracticeExam exam = practiceExamService.listExamForStudent(studentRegistration);
        return new ResponseEntity<>(PracticeExamResponseDto.toResponseDto(exam), HttpStatus.OK);
    }

    @GetMapping("/exam/student/{studentRegistration}/score")
    private ResponseEntity<ExamResultResponseDto> getIndividualExamResult(@PathVariable("studentRegistration") Integer studentRegistration) {
        PracticeExam exam = practiceExamService.evaluateIndividualExam(studentRegistration);
        return new ResponseEntity<>(ExamResultResponseDto.toResponseDto(exam), HttpStatus.OK);
    }

    @GetMapping("/exams/students/score")
    private ResponseEntity<List<ExamResultResponseDto>> getBestFiveRanking() {
        List<ExamResultResponseDto> exams = practiceExamService.evaluateBestStudents()
                .stream().map(ExamResultResponseDto::toResponseDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

}
