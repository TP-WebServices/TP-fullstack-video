package com.ynov.webfullstack.tp.video.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Entity
public class Tag {
    @Id @GeneratedValue
    private UUID uuid;
    @NotNull
    private String title;

    public Tag() {
    }

    public Tag(String title) {
        this.uuid = UUID.randomUUID();
        this.title = title;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}