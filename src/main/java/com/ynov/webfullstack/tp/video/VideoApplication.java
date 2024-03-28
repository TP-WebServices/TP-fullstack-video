package com.ynov.webfullstack.tp.video;

import com.ynov.webfullstack.tp.video.models.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VideoApplication {

	public static void main(String[] args) {

		new Role("Default", "Default role for new users");
		SpringApplication.run(VideoApplication.class, args);


	}

}
