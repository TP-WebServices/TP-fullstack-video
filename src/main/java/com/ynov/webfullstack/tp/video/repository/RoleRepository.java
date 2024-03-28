package com.ynov.webfullstack.tp.video.repository;

import com.ynov.webfullstack.tp.video.models.Role;
import com.ynov.webfullstack.tp.video.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByTitle(String title);
    List<Utilisateur> findUtilisateursByTitle(String title);
}