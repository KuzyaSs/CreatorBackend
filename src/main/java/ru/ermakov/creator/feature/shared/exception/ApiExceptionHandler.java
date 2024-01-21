package ru.ermakov.creator.feature.shared.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.ermakov.creator.feature.follow.exception.FollowNotFoundException;
import ru.ermakov.creator.feature.creditGoal.exception.CreditGoalNotFoundException;
import ru.ermakov.creator.feature.subscription.exception.DuplicateSubscriptionTitleException;
import ru.ermakov.creator.feature.subscription.exception.SubscriptionNotFoundException;
import ru.ermakov.creator.feature.transaction.exception.InsufficientFundsInAccountException;
import ru.ermakov.creator.feature.transaction.exception.InsufficientFundsInGoalException;
import ru.ermakov.creator.feature.transaction.exception.TransactionNotFoundException;
import ru.ermakov.creator.feature.user.exception.DuplicateUsernameException;
import ru.ermakov.creator.feature.user.exception.UserNotFoundException;
import ru.ermakov.creator.feature.userSubscription.exception.DuplicateUserSubscriptionException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {InsufficientFundsInAccountException.class, InsufficientFundsInGoalException.class})
    public ResponseEntity<Object> handleBadRequestException(RuntimeException e, HttpServletRequest request) {
        ApiExceptionBody apiExceptionBody = new ApiExceptionBody(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiExceptionBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserNotFoundException.class,
            FollowNotFoundException.class,
            SubscriptionNotFoundException.class,
            CreditGoalNotFoundException.class,
            TransactionNotFoundException.class
    })
    public ResponseEntity<Object> handleNotFoundException(RuntimeException e, HttpServletRequest request) {
        ApiExceptionBody apiExceptionBody = new ApiExceptionBody(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiExceptionBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DuplicateUsernameException.class,
            DuplicateSubscriptionTitleException.class,
            DuplicateUserSubscriptionException.class
    })
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
