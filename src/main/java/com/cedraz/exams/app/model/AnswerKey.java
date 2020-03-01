package com.cedraz.exams.app.model;

import com.cedraz.exams.app.model.constant.Answer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class AnswerKey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "test_correct_answers", joinColumns = @JoinColumn(name = "answer_key_id"))
    @Enumerated(EnumType.ORDINAL)
    private List<Answer> correctAnswers;

    @OneToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

}
