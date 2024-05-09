package com.pjt.core.common.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

//@Configuration
public class CustomObjectMapper {

	//@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(String.class, new XssSerializer());
		objectMapper.registerModule(module);
		
		return objectMapper;
	}

	public ObjectMapper get() {
	ObjectMapper objectMapper = new ObjectMapper();
	
	SimpleModule module = new SimpleModule();
	module.addSerializer(String.class, new XssSerializer());
	objectMapper.registerModule(module);
	
	return objectMapper;
	}
	
    public static CustomObjectMapper getInstance() {
        return CustomObjectMapper.InnerCustomObjectMapper.instance;
    }
	
    
    private static class InnerCustomObjectMapper {
        private static final CustomObjectMapper instance = new CustomObjectMapper();

        private InnerCustomObjectMapper() {
        }
    }
	
	
}
