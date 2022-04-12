package com.personal.estacionamiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.personal.estacionamiento.repository")
public class EstacionamientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstacionamientoApplication.class, args);
	}

}
