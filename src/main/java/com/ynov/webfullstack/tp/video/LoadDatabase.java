package com.ynov.webfullstack.tp.video;

import com.ynov.webfullstack.tp.video.models.Role;
import com.ynov.webfullstack.tp.video.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class LoadDatabase {


    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository) {
        return args -> {
            if(roleRepository.findByTitle("visiteur") == null) {
                Role role = new Role("visiteur", "Peut voir les vidéos");
                roleRepository.save(role);
            }
            System.out.println("Database initialized");
        };
    }
}
