package com.cedraz.exams.app.exception;

import com.cedraz.exams.app.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomEntityExceptionHandler {

    @ExceptionHandler(ApplicationException.EntityNotFoundException.class)
    public final ResponseEntity handleNotFountExceptions(Exception ex, WebRequest request) {
        ResponseDto response = ResponseDto.notFound();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApplicationException.DuplicateEntityException.class)
    public final ResponseEntity handleNotFountExceptions1(Exception ex, WebRequest request) {
        ResponseDto response = ResponseDto.duplicateEntity();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity(response, HttpStatus.CONFLICT);
    }

}

