package com.ainterview.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.ainterview.backend.model.User;
import com.ainterview.backend.repository.UserRepository;


@SpringBootApplication
public class BackendApplication {
	@Bean
	CommandLineRunner runner(UserRepository repo) {
		return args -> {
			if (repo.findByEmail("test@ai.com").isEmpty()) {
				repo.save(User.builder()
						.email("test@ai.com")
						.password("123456")
						.role("USER")
						.build());
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
