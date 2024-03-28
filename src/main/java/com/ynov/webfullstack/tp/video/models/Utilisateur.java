package com.ynov.webfullstack.tp.video.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue()
    private UUID id;
    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    private String password;
    @ManyToMany
    @JoinTable(
            name = "utilisateur_role",
            joinColumns = @JoinColumn(name = "utlisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "utilisateur_id"))
    private List<Role> roles;

    public Utilisateur() {
    }

    public Utilisateur(Role role) {
        this.addRole(role);
    }


    public UUID getId() {
        return id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

}

