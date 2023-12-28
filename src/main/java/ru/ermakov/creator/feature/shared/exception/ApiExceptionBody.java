package ru.ermakov.creator.feature.shared.exception;

import java.time.LocalDateTime;

public record ApiExceptionBody(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String path
) {
}
