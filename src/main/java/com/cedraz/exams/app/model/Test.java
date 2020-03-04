package com.cedraz.exams.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tests")
@Getter
@Setter
@NoArgsConstructor
public class Test implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @ManyToMany
    @Size(min=10, max=10)
    @JoinTable(name = "test_question", joinColumns = @JoinColumn(name = "test_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questions;

    @JoinColumn(unique = true, nullable = false)
    @OneToOne(mappedBy = "test", cascade = CascadeType.ALL)
    private AnswerKey answerKey;

    private int score;

    public Test(List<Question> questions, AnswerKey answerKey) {
        this.questions = questions;
        this.answerKey = answerKey;
    }

}
