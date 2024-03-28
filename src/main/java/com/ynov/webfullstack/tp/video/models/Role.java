package com.ynov.webfullstack.tp.video.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private List<Utilisateur> utilisateurs;

    public Role() {
    }

    public Role(UUID id, String title, String description) {
        this.id = id;
        this.title  = title;
        this.description = description;


    }

    public Role(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Utilisateur> getUsers() {
        return utilisateurs;
    }
}