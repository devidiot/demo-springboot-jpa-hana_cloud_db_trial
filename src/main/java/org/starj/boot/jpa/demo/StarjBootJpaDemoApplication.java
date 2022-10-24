package org.starj.boot.jpa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StarjBootJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarjBootJpaDemoApplication.class, args);
	}

}
