package com.cedraz.exams.app.service;

import com.cedraz.exams.app.dto.AnswerDto;

public interface TestService {

    void answerOneQuestion(long testId, long questionId, AnswerDto answer);

}
