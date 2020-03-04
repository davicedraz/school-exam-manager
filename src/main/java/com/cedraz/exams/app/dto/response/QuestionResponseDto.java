package com.cedraz.exams.app.dto.response;

import com.cedraz.exams.app.model.Question;
import com.cedraz.exams.app.model.constant.Answer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionResponseDto {

    private long id;
    private String title;
    private String description;
    private String subject;
    private Answer answer;

    public static QuestionResponseDto toResponseDto(Question question) {
        return new QuestionResponseDto(question.getId(), question.getTitle(),
                question.getDescription(), question.getSubject(), question.getAnswer());
    }

}
