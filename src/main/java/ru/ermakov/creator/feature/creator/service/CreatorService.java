package ru.ermakov.creator.feature.creator.service;

import ru.ermakov.creator.feature.creator.model.Creator;

import java.util.List;

public interface CreatorService {
    List<Creator> getCreatorPageBySearchQuery(String searchQuery, Integer limit, Integer offset);

    Creator getCreatorByUserId(String userId);

}
