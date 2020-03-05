package com.cedraz.exams.app.dto;

import com.cedraz.exams.app.exception.ResponseError;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDto<T> {

    private Status status;
    private Object errors;
    private T payload;

    public enum Status {
        EXCEPTION, NOT_FOUND, DUPLICATE_ENTITY
    }

    public static <T> ResponseDto<T> exception() {
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(Status.EXCEPTION);
        return response;
    }

    public static <T> ResponseDto<T> notFound() {
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(Status.NOT_FOUND);
        return response;
    }

    public static <T> ResponseDto<T> duplicateEntity() {
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(Status.DUPLICATE_ENTITY);
        return response;
    }

    public void addErrorMsgToResponse(String errorMsg, Exception ex) {
        ResponseError error = new ResponseError()
                .setDetails(errorMsg)
                .setMessage(ex.getMessage())
                .setTimestamp(new Date());
        setErrors(error);
    }

}
