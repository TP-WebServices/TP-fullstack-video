package com.ynov.webfullstack.tp.video.repository;

import com.ynov.webfullstack.tp.video.models.Utilisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, UUID> {
    boolean existsByUsernameAndIdNot(String username, UUID uuid);
}
