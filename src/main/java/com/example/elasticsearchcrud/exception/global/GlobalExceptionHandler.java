package com.example.elasticsearchcrud.exception.global;

import com.example.elasticsearchcrud.exception.custom.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleException(BookNotFoundException e) {
        return new ResponseEntity<>("Book Not found exception occur: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
