package com.ynov.webfullstack.tp.video.Controller;

import com.ynov.webfullstack.tp.video.models.Tag;
import com.ynov.webfullstack.tp.video.models.Video;
import com.ynov.webfullstack.tp.video.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagRepository.save(tag);
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Tag> getTag(@PathVariable UUID uuid) {
        Optional<Tag> tag = tagRepository.findById(uuid);
        return tag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Tag> getTagByTitle(@PathVariable String title) {
        Optional<Tag> tag = tagRepository.findByTitle(title);
        return tag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/title/similar/{title}")
    public List<Tag> getTagByTitleSimilar(@PathVariable String title) {
        return tagRepository.findTagsByTitleContaining(title);
    }


    @PutMapping("/{uuid}")
    public ResponseEntity<Tag> updateTag(@PathVariable UUID uuid, @RequestBody Tag tagDetails) {
        Optional<Tag> tag = tagRepository.findById(uuid);
        if (tag.isPresent()) {
            Tag updatedTag = tag.get();
            updatedTag.setTitle(tagDetails.getTitle());
            tagRepository.save(updatedTag);
            return ResponseEntity.ok(updatedTag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID uuid) {
        Optional<Tag> tag = tagRepository.findById(uuid);
        if (tag.isPresent()) {
            tagRepository.delete(tag.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}