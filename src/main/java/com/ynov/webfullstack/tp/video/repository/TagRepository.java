package com.ynov.webfullstack.tp.video.repository;

import com.ynov.webfullstack.tp.video.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
    public Optional<Tag> findByTitle(String title);
    public List<Tag> findTagsByTitleContaining(String title);
}