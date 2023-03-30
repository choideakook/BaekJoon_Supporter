package com.huh.BaekJoonSupporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BaekJoonSupporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaekJoonSupporterApplication.class, args);
	}

}
