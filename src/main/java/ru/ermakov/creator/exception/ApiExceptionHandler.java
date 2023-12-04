package ru.ermakov.creator.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {UserNotFoundException.class, FollowNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(RuntimeException e, HttpServletRequest request) {
        ApiExceptionBody apiExceptionBody = new ApiExceptionBody(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiExceptionBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UsernameInUseException.class})
    public ResponseEntity<Object> handleConflictException(RuntimeException e, HttpServletRequest request) {
        ApiExceptionBody apiExceptionBody = new ApiExceptionBody(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiExceptionBody, HttpStatus.CONFLICT);
    }
}
