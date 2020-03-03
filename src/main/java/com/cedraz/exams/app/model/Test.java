package com.cedraz.exams.app.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="tests")
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=10, max=10)
    @OneToMany(mappedBy = "test")
    private List<Question> questions;

    @JoinColumn(unique = true, nullable = false)
    @OneToOne(mappedBy = "test", cascade = CascadeType.ALL)
    private AnswerKey answerKey;

    @ManyToOne
    @JoinColumn(name="practice_exam_id")
    private PracticeExam exam;

    private int score;

    public Test(List<Question> questions, AnswerKey answerKey, PracticeExam exam) {
        this.questions = questions;
        this.answerKey = answerKey;
        this.exam = exam;
    }

}
