package ru.ermakov.creator.model;

public record UserCategory(
        Long id,
        String name,
        Boolean isSelected
) {
}
