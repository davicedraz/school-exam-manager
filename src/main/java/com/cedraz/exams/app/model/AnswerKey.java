package com.cedraz.exams.app.model;

import com.cedraz.exams.app.model.constant.Answer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AnswerKey implements Serializable {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "test_correct_answers", joinColumns = @JoinColumn(name = "answer_key_id"))
    @Enumerated(EnumType.ORDINAL)
    private List<Answer> correctAnswers;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "test_id")
    private Test test;

    public AnswerKey(List<Answer> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
