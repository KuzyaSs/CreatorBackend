package ru.ermakov.creator.feature.userSubscription.model;


import ru.ermakov.creator.feature.subscription.model.Subscription;
import ru.ermakov.creator.feature.user.model.User;

import java.sql.Timestamp;

public record UserSubscription(
        Long id,
        Subscription subscription,
        User user,
        Timestamp startDate,
        Timestamp endDate
) {
}
