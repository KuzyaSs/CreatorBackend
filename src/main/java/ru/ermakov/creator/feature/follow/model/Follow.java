package ru.ermakov.creator.feature.follow.model;


import ru.ermakov.creator.feature.creator.model.Creator;
import ru.ermakov.creator.feature.user.model.User;

import java.sql.Timestamp;

public record Follow(
        Long id,
        User user,
        Creator creator,
        Timestamp startDate
) {
}
