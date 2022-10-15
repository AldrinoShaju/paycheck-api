package com.familyshop.paybackcheckapi.exception;

import com.familyshop.paybackcheckapi.model.ExceptionResponse;
import com.familyshop.paybackcheckapi.model.Response;
import com.mongodb.MongoCommandException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MongoCommandException.class)
    public ResponseEntity handleMongoException(MongoCommandException e) {
        ExceptionResponse errorResponse = new ExceptionResponse(e.getErrorCodeName(),e.getErrorMessage(), new Date().toString());
        Response<ExceptionResponse> response = new Response<>(e.getErrorCode(), errorResponse);
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
