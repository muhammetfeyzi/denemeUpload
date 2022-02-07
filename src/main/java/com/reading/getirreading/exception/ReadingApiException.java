package com.reading.getirreading.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReadingApiException extends RuntimeException {

    private final ErrorEnum errorCode;

    public ReadingApiException(ErrorEnum errorCode) {
        super();
        this.errorCode = errorCode;
    }

}