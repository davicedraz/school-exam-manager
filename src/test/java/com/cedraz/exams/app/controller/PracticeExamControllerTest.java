package com.cedraz.exams.app.controller;

import com.cedraz.exams.app.dto.AnswerDto;
import com.cedraz.exams.app.model.constant.Answer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PracticeExamControllerTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();
    private ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

    @Test
    public void getIndividualScore() throws Exception {
        answerQuestionCorrectly(31, 21, Student.student1.id, Answer.A);

        mvc.perform(get("/api/v1/exam/student/"+ Student.student1.id + "/score")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.score", is(612))));
    }

    @Test
    public void rakingExamsResults() throws Exception {
        answerQuestionCorrectly(43, 33, Student.student2.id, Answer.A);
        answerQuestionCorrectly(55, 45, Student.student3.id, Answer.A);
        answerQuestionCorrectly(67, 57, Student.student4.id, Answer.A);
        answerQuestionCorrectly(79, 69, Student.student5.id, Answer.A);

        answerQuestionCorrectly(55, 46, Student.student3.id, Answer.D);

        mvc.perform(get("/api/v1/exams/students/score")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$[0].student.id", is(Student.student3.id))));
    }

    private void answerQuestionCorrectly(int testId, int questionId, int studentId, Answer correctAnswer) throws Exception  {
        AnswerDto answer = new AnswerDto();
        answer.setAnswer(correctAnswer);
        String requestJson = ow.writeValueAsString(answer);

        mvc.perform(patch("/api/v1/test/" + testId + "/question/" + questionId)
                .header("Student-Id", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    private enum Student {
        student1(1), student2(2), student3(3), student4(4), student5(5);
        private int id;

        Student(int id) {
            this.id = id;
        }
    }

}


