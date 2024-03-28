package com.ynov.webfullstack.tp.video.Controller;

import com.ynov.webfullstack.tp.video.models.Tag;
import com.ynov.webfullstack.tp.video.models.Video;
import com.ynov.webfullstack.tp.video.repository.TagRepository;
import com.ynov.webfullstack.tp.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private TagRepository tagRepository;

    @PostMapping
    public ResponseEntity<Video> createVideo(@RequestBody Video video) {
        Optional<Video> images = videoRepository.findById(video.getUuid());
        if (images.isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        Video savedImage = videoRepository.save(video);
        return ResponseEntity.ok(video);
    }

    @GetMapping
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Video> getVideo(@PathVariable UUID uuid) {
        Optional<Video> video = videoRepository.findById(uuid);
        return video.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Video> getVideoByTitle(@PathVariable String title) {
        Optional<Video> video = videoRepository.findByTitle(title);
        return video.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tags/{titleTag}")
    public List<Video> getVideoByTagTitle(@PathVariable String titleTag) {
        return videoRepository.findByTagsTitle(titleTag);
    }

    @GetMapping("/titleOrDescription/{needle}")
    public List<Video> getVideoByTitleOrShortDescription(@PathVariable String needle) {
        return videoRepository.findVideosByTitleContainingOrShortDescriptionContaining(needle, needle);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Video> updateVideo(@PathVariable UUID uuid, @RequestBody Video videoDetails) {
        Optional<Video> video = videoRepository.findById(uuid);
        if (video.isPresent()) {
            Video updatedVideo = video.get();
            updatedVideo.setTitle(videoDetails.getTitle());
            updatedVideo.setShortDescription(videoDetails.getShortDescription());
            updatedVideo.setLongDescription(videoDetails.getLongDescription());
            updatedVideo.setTags(videoDetails.getTags());
            videoRepository.save(updatedVideo);
            return ResponseEntity.ok(updatedVideo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteVideo(@PathVariable UUID uuid) {
        Optional<Video> video = videoRepository.findById(uuid);
        if (video.isPresent()) {
            videoRepository.delete(video.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//a3879444-fcc7-49a0-ba7b-62962ebc35d2
    @PatchMapping("/{videoUuid}/tag/{tagUuid}")
    public ResponseEntity<Video> addTagToVideo(@PathVariable UUID videoUuid, @PathVariable UUID tagUuid) {
        Optional<Video> video = videoRepository.findById(videoUuid);
        Optional<Tag> tag = tagRepository.findById(tagUuid);
        if (video.isPresent() && tag.isPresent()) {
            Video updatedVideo = video.get();
            if (updatedVideo.getTags().contains(tag.get())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            updatedVideo.addTag(tag.get());
            videoRepository.save(updatedVideo);
            return ResponseEntity.ok(updatedVideo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}