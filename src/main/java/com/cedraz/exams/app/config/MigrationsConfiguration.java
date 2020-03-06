package com.cedraz.exams.app.config;

import com.cedraz.exams.app.model.AnswerKey;
import com.cedraz.exams.app.model.PracticeExam;
import com.cedraz.exams.app.model.Question;
import com.cedraz.exams.app.model.Test;
import com.cedraz.exams.app.model.constant.Answer;
import com.cedraz.exams.app.repository.AnswerKeyRepository;
import com.cedraz.exams.app.repository.PracticeExamRepository;
import com.cedraz.exams.app.repository.QuestionRepository;
import com.cedraz.exams.app.repository.TestRepository;

import org.flywaydb.core.Flyway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class MigrationsConfiguration {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerKeyRepository answerKeyRepository;

    @Autowired
    private PracticeExamRepository practiceExamRepository;

    @Autowired
    public MigrationsConfiguration(DataSource dataSource) {
        Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadDefaultData() {
        List<Question> questions = new ArrayList<>(questionRepository.findAll());
        List<PracticeExam> practiceExams = new ArrayList<>(practiceExamRepository.findAll());
        List<Answer> correctAnswers = questions.stream().map(Question::getCorrectAnswer).collect(Collectors.toList());

        practiceExams.forEach(exam -> {
            AnswerKey answerKey = new AnswerKey(correctAnswers);
            List<Question> newQuestions = questions.stream().map(question -> {
                Question q = new Question(question.getTitle(), question.getDescription(),
                        question.getSubject(), question.getCorrectAnswer(), question.getDifficulty());
                questionRepository.save(q);
                return q;
            }).collect(Collectors.toList());

            Test test = testRepository.save(new Test(newQuestions, answerKey));

            exam.getTests().add(test);
            practiceExamRepository.save(exam);

            answerKey.setTest(test);
            answerKey.setCorrectAnswers(correctAnswers);

            this.answerKeyRepository.save(answerKey);
        });
    }

}
