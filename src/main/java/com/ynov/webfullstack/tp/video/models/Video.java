package com.ynov.webfullstack.tp.video.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@Entity
public class Video {
    @Id
    private UUID uuid;
    @NotNull
    private String title;
    @NotNull
    private String shortDescription;
    private String longDescription;
    @ManyToMany
    @JoinTable(
            name = "video_tag",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    public Video() {}

    public Video(String title, String shortDescription) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.shortDescription = shortDescription;
    }



    // getters and setters
}