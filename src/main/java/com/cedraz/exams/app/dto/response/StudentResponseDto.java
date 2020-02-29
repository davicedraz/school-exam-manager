package com.cedraz.exams.app.dto.response;

import com.cedraz.exams.app.model.Student;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StudentResponseDto {

    private long id;
    private String name;
    private int registrationNumber;

    public static StudentResponseDto toResponseDto(Student student) {
        return new StudentResponseDto(student.getId(), student.getName(), student.getRegistrationNumber());
    }

}
