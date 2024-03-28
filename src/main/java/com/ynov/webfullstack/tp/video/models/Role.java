package com.ynov.webfullstack.tp.video.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private UUID uuid;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private List<Utilisateur> utilisateurs;

    public Role() {
    }

    public Role(UUID id, String title, String description) {
        this.uuid = id;
        this.title  = title;
        this.description = description;


    }

    public Role(String title, String description) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.description = description;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public UUID getUuid() {
        return uuid;
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