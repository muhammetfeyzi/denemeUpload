package com.reading.getirreading.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/*
    Author Muhammet Feyzi Saglam
    02.06.2022
    i use Global Exception handler in spring boot project and using @ExceptionHandler anatation

 */

@RestControllerAdvice
public class ReadingGlobalExceptionHandler {

    // this ReadingApiException is custom Exceptiion Handler  class in our project.
    @ExceptionHandler(ReadingApiException.class)
    public final ResponseEntity<ReadingApiException> handleCustomerApiException(ReadingApiException ex, WebRequest request) {
        return prepareResponse(ex);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Exception> handleException(
            Exception ex) {
        return ResponseEntity.status(ErrorEnum.INTERNAL_SERVER_ERROR.getHttpStatus()).body(ex);
    }

    private static ResponseEntity<ReadingApiException> prepareResponse(ReadingApiException exception) {
        return ResponseEntity.status(exception.getErrorCode().getHttpStatus()).body(exception);
    }
}