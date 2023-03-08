package com.datapoem.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProjectManagementServiceMainClass {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementServiceMainClass.class, args);
	}

	@Bean
	 PasswordEncoder passwordEncoder() {
	    return NoOpPasswordEncoder.getInstance();
	}
}
