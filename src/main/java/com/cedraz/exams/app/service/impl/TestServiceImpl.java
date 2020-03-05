package com.cedraz.exams.app.service.impl;

import com.cedraz.exams.app.dto.AnswerDto;
import com.cedraz.exams.app.exception.ApplicationException;
import com.cedraz.exams.app.exception.EntityType;
import com.cedraz.exams.app.exception.ExceptionType;
import com.cedraz.exams.app.model.Question;
import com.cedraz.exams.app.model.Test;
import com.cedraz.exams.app.model.constant.Difficulty;
import com.cedraz.exams.app.repository.QuestionRepository;
import com.cedraz.exams.app.repository.TestRepository;
import com.cedraz.exams.app.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

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

        questionToBeAnswered.get().setAnswer(answer.getAnswer());
        testRepository.save(test); //FIXME: atualizar o practice exam do usuario logado
    }

    @Override
    public int calculateScore(Test test) {
        try {
            int totalScoreQuestions = scoreOfCorrectQuestions(test.getQuestions(), Difficulty.EASY) +
                    scoreOfCorrectQuestions(test.getQuestions(), Difficulty.MEDIUM) +
                    scoreOfCorrectQuestions(test.getQuestions(), Difficulty.HARD);

            int totalScore = totalScoreQuestions + 600;
            test.setScore(totalScore);
            testRepository.save(test);

            return totalScore;
        } catch (Error error) {
            throw exception(EntityType.SCHOOL_TEST, ExceptionType.ENTITY_EXCEPTION);
        }
    }

    private int scoreOfCorrectQuestions(List<Question> questions, Difficulty difficulty) {
        int numberOfCorrectQuestions = (int) questions.stream()
                .filter(question -> question.getDifficulty() == difficulty &&
                        question.getAnswer() == question.getCorrectAnswer()).count();

        return numberOfCorrectQuestions * difficulty.getPoints();
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ApplicationException.throwException(entityType, exceptionType, args);
    }

}
