package com.ynov.webfullstack.tp.video.repository;

import com.ynov.webfullstack.tp.video.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
}