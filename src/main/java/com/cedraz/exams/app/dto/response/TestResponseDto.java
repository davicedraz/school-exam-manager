package com.cedraz.exams.app.dto.response;

import com.cedraz.exams.app.model.Test;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TestResponseDto {

    private long id;
    private int score;
    private List<QuestionResponseDto> questions;

    public static TestResponseDto toResponseDto(Test test) {
        return new TestResponseDto(test.getId(), test.getScore(),
                test.getQuestions().stream().map(QuestionResponseDto::toResponseDto).collect(Collectors.toList()));
    }

}
