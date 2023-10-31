package com.onlineshop.OrderService.exception;

import com.onlineshop.OrderService.exception.CustomException;
import com.onlineshop.OrderService.external.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorCode(exception.getErrorCode())
                .errorMessage(exception.getMessage())
                .errorDateTime(LocalDateTime.now())
                .build(), HttpStatus.valueOf(exception.getStatus()));
    }
}
