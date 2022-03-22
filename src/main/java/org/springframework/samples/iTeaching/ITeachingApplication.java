package org.springframework.samples.iTeaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ITeachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ITeachingApplication.class, args);
		log.info("Listo para la clase");
	}

}
