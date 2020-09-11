package com.thoughtworks.capacity.gtb.mvc.handler;

import com.thoughtworks.capacity.gtb.mvc.exception.InvalidParamsException;
import com.thoughtworks.capacity.gtb.mvc.result.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



/**
 * Created by wzw on 2020/9/10.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidParamsException.class)
    public ResponseEntity<Error> handle(InvalidParamsException exception) {
        HttpStatus state = HttpStatus.BAD_REQUEST;
        Error errorError = new Error(state.value(),exception.getMessage());
        return ResponseEntity.status(state).body(errorError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handle(MethodArgumentNotValidException methodArgumentNotValidException) {
        String message = methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage();
        HttpStatus state = HttpStatus.BAD_REQUEST;
        Error errorError = new Error(state.value(),message);
        return ResponseEntity.status(state).body(errorError);
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Error> handle(BindException bindException) {
        String message = bindException.getBindingResult().getFieldError().getDefaultMessage();
        HttpStatus state = HttpStatus.BAD_REQUEST;
        Error errorError = new Error(state.value(),message);
        return ResponseEntity.status(state).body(errorError);

    }
}
