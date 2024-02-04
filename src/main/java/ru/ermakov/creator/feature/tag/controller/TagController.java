package ru.ermakov.creator.feature.tag.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.feature.tag.model.Tag;
import ru.ermakov.creator.feature.tag.model.TagRequest;
import ru.ermakov.creator.feature.tag.service.TagService;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("creators/{creatorId}/tags")
    List<Tag> getTagsByCreatorId(@PathVariable String creatorId) {
        return tagService.getTagsByCreatorId(creatorId);
    }

    @GetMapping("tags/{tagId}")
    Tag getTagById(@PathVariable Long tagId) {
        return tagService.getTagById(tagId);
    }

    @PostMapping("tags")
    void insertTag(@RequestBody TagRequest tagRequest) {
        tagService.insertTag(tagRequest);
    }

    @PutMapping("tags/{tagId}")
    void updateTag(@PathVariable Long tagId, @RequestBody TagRequest tagRequest) {
        tagService.updateTag(tagId, tagRequest);
    }

    @DeleteMapping("tags/{tagId}")
    void deleteTagById(@PathVariable Long tagId) {
        tagService.deleteTagById(tagId);
    }
}
