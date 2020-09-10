package com.thoughtworks.capacity.gtb.mvc.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by wzw on 2020/9/10.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResult> handle(CarNotFoundException ex) {
//        ErrorResult errorResult = new ErrorResult(ex.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
//    }
}
