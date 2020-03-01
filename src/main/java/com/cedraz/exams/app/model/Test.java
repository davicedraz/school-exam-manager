package com.cedraz.exams.app.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=10, max=10)
    @OneToMany(mappedBy = "test")
    private List<Question> questions;

    @OneToOne(mappedBy = "test")
    private AnswerKey answerKey;

    @ManyToOne
    @JoinColumn(name="practice_exam_id")
    private PracticeExam exam;

    private String subject;

    private int score;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

}
