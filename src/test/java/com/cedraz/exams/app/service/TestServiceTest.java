package com.cedraz.exams.app.service;

import com.cedraz.exams.app.model.AnswerKey;
import com.cedraz.exams.app.model.Question;
import com.cedraz.exams.app.model.Test;
import com.cedraz.exams.app.model.constant.Answer;
import com.cedraz.exams.app.model.constant.Difficulty;
import com.cedraz.exams.app.repository.TestRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestServiceTest {

    @Autowired
    private TestService testService;

    @MockBean
    private TestRepository testRepository;

    private Test test;

    private final int MAX_SCORE = 717;
    private final int MINIMUM_SCORE = 600;

    @Before
    public void setup() {
        List<Question> questions = mockQuestions();
        List<Answer> answers = questions.stream()
                .map(Question::getCorrectAnswer)
                .collect(Collectors.toList());

        test = new Test();
        test.setId(1);
        test.setQuestions(questions);
        test.setAnswerKey(new AnswerKey(answers));
    }

    @org.junit.Test
    public void achieveMaxiumScore() {
        test.getQuestions().forEach(question -> question.setAnswer(question.getCorrectAnswer()));

        int testScore = testService.calculateScore(test);
        Assert.assertEquals(MAX_SCORE, testScore);
    }

    @org.junit.Test
    public void achieveMinimumScore() {
        test.getQuestions().forEach(question -> question.setAnswer(Answer.E));

        int testScore = testService.calculateScore(test);
        Assert.assertEquals(MINIMUM_SCORE, testScore);
    }

    private List<Question> mockQuestions() {
        List<Question> questions = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Question question = new Question();
            question.setId(1 + i);
            question.setCorrectAnswer(Answer.B);
            question.setDifficulty(Difficulty.HARD);
            questions.add(question);
        }

        for (int i = 0; i < 3; i++) {
            Question question = new Question();
            question.setId(4 + i);
            question.setCorrectAnswer(Answer.B);
            question.setDifficulty(Difficulty.EASY);
            questions.add(question);
        }

        for (int i = 0; i < 4; i++) {
            Question question = new Question();
            question.setId(6 + i);
            question.setCorrectAnswer(Answer.B);
            question.setDifficulty(Difficulty.MEDIUM);
            questions.add(question);
        }

        return questions;
    }

}
