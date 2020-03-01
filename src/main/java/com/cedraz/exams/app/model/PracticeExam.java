package com.cedraz.exams.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class PracticeExam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Date date;

    @NotNull
    private String grade;

    @Column(name="total_score")
    private int totalScore;

    @NotNull
    @OneToMany(mappedBy = "exam")
    private List<Test> tests;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
