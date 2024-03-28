package com.ynov.webfullstack.tp.video.repository;

import com.ynov.webfullstack.tp.video.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VideoRepository extends JpaRepository<Video, UUID> {
}
