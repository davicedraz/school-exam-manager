package com.cedraz.exams.app.model;

import com.cedraz.exams.app.model.constant.Answer;
import com.cedraz.exams.app.model.constant.Difficulty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name="test_id")
    private Test test;

    @NotNull
    @Column(name="correct_answer")
    @Enumerated(EnumType.ORDINAL)
    private Answer correctAnswer;

    @Enumerated(EnumType.ORDINAL)
    private Answer answer;

    @Transient
    private Difficulty difficulty;

    @Basic
    @NotNull
    @Column(name="point_value")
    private int pointValue;

    @PostLoad
    void fillTransient() {
        if (pointValue > 0) {
            this.difficulty = Difficulty.of(pointValue);
        }
    }

    @PrePersist
    void fillPersistent() {
        if (difficulty != null) {
            this.pointValue = difficulty.getPoints();
        }
    }

}
