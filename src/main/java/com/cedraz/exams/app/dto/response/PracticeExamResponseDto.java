package com.cedraz.exams.app.dto.response;

import com.cedraz.exams.app.model.PracticeExam;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PracticeExamResponseDto {

    private long id;
    private Date date;
    private String grade;
    private Integer totalScore;
    private List<TestResponseDto> tests;
    private Integer studentRegistrationNumber;

    public static PracticeExamResponseDto toResponseDto(PracticeExam practiceExam) {
        return new PracticeExamResponseDto(practiceExam.getId(), practiceExam.getDate(),
                practiceExam.getGrade(), practiceExam.getTotalScore(), practiceExam.getTests().stream().map(TestResponseDto::toResponseDto).collect(Collectors.toList()),
                practiceExam.getStudent().getRegistrationNumber());
    }

}
