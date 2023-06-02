package com.example.demo.templates;

import com.example.demo.controllers.CinemaRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {CinemaRestController.BadRequestException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex) {

        return ResponseEntity.badRequest().body(new MyExceptionResponse(
                Timestamp.valueOf(LocalDateTime.now()),HttpStatus.BAD_REQUEST,ex.getMessage()));
    }
}
