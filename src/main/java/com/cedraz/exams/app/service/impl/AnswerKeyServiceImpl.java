package com.cedraz.exams.app.service.impl;

import com.cedraz.exams.app.exception.ApplicationException;
import com.cedraz.exams.app.exception.EntityType;
import com.cedraz.exams.app.exception.ExceptionType;
import com.cedraz.exams.app.model.AnswerKey;
import com.cedraz.exams.app.repository.AnswerKeyRepository;
import com.cedraz.exams.app.service.AnswerKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class AnswerKeyServiceImpl implements AnswerKeyService {

    @Autowired
    AnswerKeyRepository answerKeyRepository;

    @Override
    public AnswerKey getAnswerKey(long testId) {
        AnswerKey answerKey = answerKeyRepository.findByTest_Id(testId);
        if(answerKey == null) throw exception(EntityType.ANSWER_KEY, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(testId));
        return answerKey;
    }


    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ApplicationException.throwException(entityType, exceptionType, args);
    }
}
