package com.pjt.core;

import com.pjt.core.common.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class CorePjtApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorePjtApplication.class, args);
	}

}
