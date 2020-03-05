package com.cedraz.exams.app.dto.response;

import com.cedraz.exams.app.model.PracticeExam;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExamResultResponseDto {

    private int score;
    private StudentResponseDto student;

    public static ExamResultResponseDto toResponseDto(PracticeExam exam) {
        return new ExamResultResponseDto(exam.getTotalScore(), StudentResponseDto.toResponseDto(exam.getStudent()));
    }

}
