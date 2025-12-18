package com.guilherme.AppRH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AppRhApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppRhApplication.class, args);
	}

}
