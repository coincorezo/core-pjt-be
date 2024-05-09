package com.pjt.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pjt.core"})
@RequiredArgsConstructor
public class CorePjtApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorePjtApplication.class, args);
	}

}
