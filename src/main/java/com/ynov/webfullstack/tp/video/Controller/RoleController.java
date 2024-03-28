package com.ynov.webfullstack.tp.video.Controller;

import com.ynov.webfullstack.tp.video.models.Role;
import com.ynov.webfullstack.tp.video.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Role> getRole(@PathVariable UUID uuid) {
        Optional<Role> role = roleRepository.findById(uuid);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Role> updateRole(@PathVariable UUID uuid, @RequestBody Role roleDetails) {
        Optional<Role> role = roleRepository.findById(uuid);
        if (role.isPresent()) {
            Role updatedRole = role.get();
            updatedRole.setTitle(roleDetails.getTitle());
            roleRepository.save(updatedRole);
            return ResponseEntity.ok(updatedRole);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID uuid) {
        Optional<Role> role = roleRepository.findById(uuid);
        if (role.isPresent()) {
            roleRepository.delete(role.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
