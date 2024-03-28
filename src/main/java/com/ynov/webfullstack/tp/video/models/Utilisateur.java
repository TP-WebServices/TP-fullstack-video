package com.ynov.webfullstack.tp.video.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "utilisateur_role",
            joinColumns = @JoinColumn(name = "utlisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "utilisateur_id"))
    private List<Role> roles;

    public Utilisateur() {
        roles = new ArrayList<>();
    }


    public UUID getId() {
        return id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

}

