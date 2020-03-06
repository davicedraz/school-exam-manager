package com.cedraz.exams.app.controller;

import com.cedraz.exams.app.dto.AnswerDto;
import com.cedraz.exams.app.model.AnswerKey;
import com.cedraz.exams.app.model.Question;
import com.cedraz.exams.app.service.AnswerKeyService;
import com.cedraz.exams.app.service.TestService;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Api(description = "Operations pertaining to School Tests")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    AnswerKeyService answerKeyService;

    @PatchMapping("/test/{testId}/question/{questionId}")
    private ResponseEntity<Question> saveQuestionAnswer(@RequestHeader("Student-Id") long studentId, @PathVariable("testId") long testId,
            @PathVariable("questionId") long questionId, @RequestBody @Valid AnswerDto answer) {
        testService.answerOneQuestion(studentId, testId, questionId, answer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test/{testId}/key")
    private ResponseEntity<AnswerKey> getTestAnswerKey(@PathVariable("testId") long testId) {
        AnswerKey answerKey = answerKeyService.getAnswerKey(testId);
        return new ResponseEntity<>(answerKey, HttpStatus.OK);
    }

}
