package com.pard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PardApplication {

	public static void main(String[] args) {
		SpringApplication.run(PardApplication.class, args);
	}
}
