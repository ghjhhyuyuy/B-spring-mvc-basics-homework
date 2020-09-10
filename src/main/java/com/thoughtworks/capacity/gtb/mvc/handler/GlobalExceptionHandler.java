package com.thoughtworks.capacity.gtb.mvc.handler;

import com.thoughtworks.capacity.gtb.mvc.exception.InvalidParamsException;
import com.thoughtworks.capacity.gtb.mvc.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by wzw on 2020/9/10.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidParamsException.class)
    public ResponseEntity<Result> handle(InvalidParamsException exception) {
        Result errorResult = new Result(HttpStatus.BAD_REQUEST.value(),exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result> handle(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        Result errorResult = new Result(HttpStatus.BAD_REQUEST.value(),message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
