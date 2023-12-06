package ru.ermakov.creator.service;

import ru.ermakov.creator.model.Creator;

import java.util.List;

public interface CreatorService {
    List<Creator> getCreatorsByPage(String searchQuery, Integer limit, Integer offset);

    Creator getCreatorByUserId(String userId);

}
