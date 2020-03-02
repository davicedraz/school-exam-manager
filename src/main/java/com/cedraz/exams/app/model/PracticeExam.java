package com.cedraz.exams.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class PracticeExam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Date date;

    @NotNull
    private String grade;

    @Column(name="total_score")
    private Integer totalScore;

    @NotNull
    @OneToMany(mappedBy = "exam")
    private List<Test> tests;

    @OneToOne
    @JoinColumn(name = "student_number", referencedColumnName = "registration_number")
    private Student student;

}
