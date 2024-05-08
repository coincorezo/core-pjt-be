package com.pjt.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pjt.core"})
@RequiredArgsConstructor
//@ComponentScan(basePackages = {"com.pjt.core"}, includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {XssConverter.class}))
public class CorePjtApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorePjtApplication.class, args);
	}

}
