package com.ynov.webfullstack.tp.video.repository;

import com.ynov.webfullstack.tp.video.models.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, UUID> {
    boolean existsByUsernameAndIdNot(String username, UUID uuid);

    @Query("SELECT u FROM Utilisateur u JOIN u.roles r WHERE r.title = :title")
    List<Utilisateur> findUtilisateursByRoleTitle(String title);

    List<Utilisateur> findByUsernameContains(String username);
}
