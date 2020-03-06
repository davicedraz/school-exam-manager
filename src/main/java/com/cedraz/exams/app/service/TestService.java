package com.cedraz.exams.app.service;

import com.cedraz.exams.app.dto.AnswerDto;
import com.cedraz.exams.app.model.Test;

public interface TestService {

    int calculateScore(Test test);
    void answerOneQuestion(long studentId, long testId, long questionId, AnswerDto answer);

}
