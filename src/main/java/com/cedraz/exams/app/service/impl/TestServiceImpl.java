package com.cedraz.exams.app.service.impl;

import com.cedraz.exams.app.dto.AnswerDto;
import com.cedraz.exams.app.exception.ApplicationException;
import com.cedraz.exams.app.exception.EntityType;
import com.cedraz.exams.app.exception.ExceptionType;
import com.cedraz.exams.app.model.Question;
import com.cedraz.exams.app.model.Test;
import com.cedraz.exams.app.model.constant.Answer;
import com.cedraz.exams.app.repository.QuestionRepository;
import com.cedraz.exams.app.repository.TestRepository;
import com.cedraz.exams.app.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    TestRepository testRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public void answerOneQuestion(long testId, long questionId, AnswerDto answer) {
        Optional<Test> testToBeAnswered = testRepository.findById(testId);

        if(!testToBeAnswered.isPresent())
            throw exception(EntityType.SCHOOL_TEST, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(testId));
        Test test = testToBeAnswered.get();

        Optional<Question> questionToBeAnswered = test.getQuestions()
                .stream().filter(question -> question.getId() == questionId).findFirst();

        if(!questionToBeAnswered.isPresent())
            throw exception(EntityType.QUESTION_TEST, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(testId));
        Question question = questionToBeAnswered.get();

        Integer scoreHandle = shouldIncreaseOrDecrease(question, answer.getAnswer());

        if (scoreHandle != 0) {
            if(scoreHandle > 0)
                increaseTestScore(test, question);
            else
                decreaseTestScore(test, question);
        }

        question.setAnswer(answer.getAnswer());
        questionRepository.save(question);
    }

    private Integer shouldIncreaseOrDecrease(Question question, Answer answer) {
        if(answer == question.getCorrectAnswer()) {
            if (question.getAnswer() == null || question.getAnswer() != answer)
                return 1;
        } else if (question.getAnswer() != null) {
            if(question.getAnswer() == question.getCorrectAnswer()) {
                return -1;
            }
        }
        return 0;
    }

    private void increaseTestScore(Test test, Question question) {
        test.setScore(test.getScore() + question.getPointValue());
        testRepository.save(test);
    }

    private void decreaseTestScore(Test test, Question question) {
        test.setScore(test.getScore() - question.getPointValue());
        testRepository.save(test);
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ApplicationException.throwException(entityType, exceptionType, args);
    }

}
