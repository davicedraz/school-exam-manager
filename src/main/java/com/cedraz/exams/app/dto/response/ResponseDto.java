package com.cedraz.exams.app.dto.response;

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
        OK, BAD_REQUEST,
        UNAUTHORIZED, VALIDATION_EXCEPTION,
        EXCEPTION, WRONG_CREDENTIALS, ACCESS_DENIED, NOT_FOUND, DUPLICATE_ENTITY
    }

    public static <T> ResponseDto<T> badRequest() { //TODO: Remove if not used
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(Status.BAD_REQUEST);
        return response;
    }

    public static <T> ResponseDto<T> ok() { //TODO: Remove if not used
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(Status.OK);
        return response;
    }

    public static <T> ResponseDto<T> unauthorized() { //TODO: Remove if not used
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(Status.UNAUTHORIZED);
        return response;
    }

    public static <T> ResponseDto<T> validationException() { //TODO: Remove if not used
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(Status.VALIDATION_EXCEPTION);
        return response;
    }

    public static <T> ResponseDto<T> wrongCredentials() { //TODO: Remove if not used
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(Status.WRONG_CREDENTIALS);
        return response;
    }

    public static <T> ResponseDto<T> accessDenied() { //TODO: Remove if not used
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(Status.ACCESS_DENIED);
        return response;
    }

    public static <T> ResponseDto<T> exception() { //TODO: Remove if not used
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
