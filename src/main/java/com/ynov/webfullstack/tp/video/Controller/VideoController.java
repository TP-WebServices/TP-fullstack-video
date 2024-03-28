package com.ynov.webfullstack.tp.video.Controller;

import com.ynov.webfullstack.tp.video.models.Video;
import com.ynov.webfullstack.tp.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Video createVideo(@RequestBody Video video) {
        return videoRepository.save(video);
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
}