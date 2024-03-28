package com.ynov.webfullstack.tp.video.Controller;

import com.ynov.webfullstack.tp.video.models.Utilisateur;
import com.ynov.webfullstack.tp.video.repository.RoleRepository;
import com.ynov.webfullstack.tp.video.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;
    @PostMapping
    public Utilisateur addUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateur.addRole(roleRepository.findByTitle("visiteur").get());
        return utilisateurRepository.save(utilisateur);
    }
    @GetMapping
    public Iterable<Utilisateur> getUtilisateurs() {return utilisateurRepository.findAll();}
    @GetMapping("/{uuid}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable UUID uuid) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(uuid);
        return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity updateUtilisateur(@PathVariable UUID uuid, @RequestBody Utilisateur utilisateurDetails) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(uuid);
        if (utilisateur.isPresent()) {
            if (utilisateurRepository.existsByUsernameAndIdNot(utilisateurDetails.getUsername(), uuid)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
            }

            Utilisateur updatedUtilisateur = utilisateur.get();
            updatedUtilisateur.setUsername(utilisateurDetails.getUsername());
            updatedUtilisateur.setPassword(utilisateurDetails.getPassword());
            updatedUtilisateur.setRoles(utilisateurDetails.getRoles());
            utilisateurRepository.save(updatedUtilisateur);
            return ResponseEntity.ok(updatedUtilisateur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/role/{title}")
    public Iterable<Utilisateur> getUtilisateursByRoleTitle(@PathVariable String title) {
        return utilisateurRepository.findUtilisateursByRoleTitle(title);
    }

    @PutMapping("/{uuid}/role/{title}")
    public ResponseEntity addRoleToUtilisateur(@PathVariable UUID uuid, @PathVariable String title) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(uuid);
        if (utilisateur.isPresent()) {
            utilisateur.get().addRole(roleRepository.findByTitle(title).get());
            utilisateurRepository.save(utilisateur.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uuid}/role/{title}")
    public void deleteUserRole(@PathVariable UUID uuid, @PathVariable String title) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(uuid);
        utilisateur.ifPresent(value -> value.getRoles().removeIf(role -> role.getTitle().equals(title)));
        utilisateurRepository.save(utilisateur.get());
    }

    @DeleteMapping("/{uuid}")
    public void deleteUtilisateur(@PathVariable UUID uuid) {utilisateurRepository.deleteById(uuid);}
}