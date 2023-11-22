package ru.ermakov.creator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String username;
    private String email;
    private String bio;
    private String profileAvatarUrl;
    private String profileBackgroundUrl;
    private Boolean isModerator;
    private LocalDate registrationDate;
}