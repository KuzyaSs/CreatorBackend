package ru.ermakov.creator.model;

public record UserCategory(
        Long id,
        String name,
        String userId,
        Boolean isSelected
) {
}
