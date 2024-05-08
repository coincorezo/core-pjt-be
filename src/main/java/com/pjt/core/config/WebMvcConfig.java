package com.pjt.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.pjt.core.common.util.CustomObjectMapper;
import com.pjt.core.common.util.XssSerializer;

//@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

//	@Bean
//	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//		ObjectMapper objectMapper = CustomObjectMapper.getInstance().get();
//		mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
//		return mappingJackson2HttpMessageConverter;
//	}

	
	
}
