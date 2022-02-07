package com.reading.getirreading.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/*
    @Author Muhammet Feyzi Saglam
    I created this enum so that when my program throws an error, it can be meaningfully managed from a single center.
    and i choose http1.0 status code managed our errors code and if you  learn more information you can look -->
    https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
 */

@Getter
// we dont use setter cause of If you declare a variable of type UserType, then the only possible values must be one of the defined enum constants
@AllArgsConstructor
public enum ErrorEnum {

    CUSTOMER_NOT_EXIST(9003, "Customer is not exist", HttpStatus.NO_CONTENT),
    BOOK_NOT_EXIST(9003, "Product is not exist", HttpStatus.NO_CONTENT),
    BOOK_ID_MUST_NOT_BLANK(9004, "Book id must not blank", HttpStatus.BAD_REQUEST),
    BOOK_NOT_AVAIBLE(9005, "Book not avaible", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(9006, "User is not exist", HttpStatus.NO_CONTENT),
    ORDER_NOT_EXIST(9006, "Order is not exist ", HttpStatus.NO_CONTENT),
    FIELD_VALIDATION_ERROR(9001, "Field validation error.", HttpStatus.BAD_REQUEST),
    CONTENT_NOT_FOUND_ERROR(9002, "Content not found.", HttpStatus.NO_CONTENT),
    INTERNAL_SERVER_ERROR(9000, "Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}