package com.ynov.webfullstack.tp.video.repository;

import com.ynov.webfullstack.tp.video.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoRepository extends JpaRepository<Video, UUID> {
    Optional<Video> findByTitle(String title);

    List<Video> findByTagsTitle(String title);
    List<Video> findVideosByTitleContainingOrShortDescriptionContaining(String needle,String needle2);
}
