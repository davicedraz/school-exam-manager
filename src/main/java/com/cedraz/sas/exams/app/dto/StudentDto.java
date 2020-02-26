package com.cedraz.sas.exams.app.dto;

import com.cedraz.sas.exams.app.model.Student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Setter;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDto {

    private String name;

    @Size(min=5, max=8)
    private String password;

    private int registrationNumber;

    public Student toObject() {
        return new Student(name, password, registrationNumber);
    }

}
