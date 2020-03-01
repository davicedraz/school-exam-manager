package com.cedraz.exams.app.model;

import com.cedraz.exams.app.model.constant.Answer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class AnswerKey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "correct_answers", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.ORDINAL)
    private List<Answer> correctAnswers;

    @OneToOne
    @JoinColumn(name = "test_id")
    private Test test;

}
