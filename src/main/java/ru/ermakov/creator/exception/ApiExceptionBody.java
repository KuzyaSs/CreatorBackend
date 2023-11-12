package ru.ermakov.creator.exception;

import java.time.LocalDateTime;

public record ApiExceptionBody(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String path
) {
}
