package ru.ermakov.creator.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleException(RuntimeException e, HttpServletRequest request) {
        ApiExceptionBody apiExceptionBody = new ApiExceptionBody(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(apiExceptionBody);
    }
}
