package ru.ermakov.creator.feature.creator.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.feature.creator.model.Creator;
import ru.ermakov.creator.feature.creator.service.CreatorService;

import java.util.List;

@RestController
@RequestMapping(path = "api/creators")
public class CreatorController {
    private final CreatorService creatorService;

    public CreatorController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    @GetMapping
    public List<Creator> getCreatorsByPage(@RequestParam String searchQuery, @RequestParam Integer limit, @RequestParam Integer offset) {
        return creatorService.getCreatorsByPage(searchQuery, limit, offset);
    }

    @GetMapping("{userId}")
    public Creator getCreatorByUserId(@PathVariable String userId) {
        return creatorService.getCreatorByUserId(userId);
    }
}
