package com.cedraz.sas.exams.app.dto;

import com.cedraz.sas.exams.app.model.Student;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDto {

    private String name;
    private String password;
    private int registrationNumber;

    public Student toObject() {
        return new Student(name, password, registrationNumber);
    }

}
