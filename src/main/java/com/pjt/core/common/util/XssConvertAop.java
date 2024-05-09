package com.pjt.core.common.util;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class XssConvertAop {
	
	@Pointcut("execution( * com.pjt.core..*Service.insert*(..)) || execution(* com.core.secta9ine.*Service.update*(..))")
	private void cut() {}


	@Before("cut()")
	public Object execut(JoinPoint joinPoint) throws IllegalArgumentException, IllegalAccessException, JsonProcessingException, ClassNotFoundException {

		Object[] args = joinPoint.getArgs();
		List<Object> convertedArgs = new ArrayList<>();
	
		for(Object arg : args) {
			if(arg != null) {
				for(Field field : arg.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					
					if(field.isAnnotationPresent(XssConverter.class)) {
						Object value = field.get(arg);
						
						String convertedData = convertData(String.valueOf(value));
						field.set(arg, convertedData);
					}
				}
 				
			}
			convertedArgs.add(args);
		}
		
	return convertedArgs.toArray();
	
	}
	
	/**
	 * Object -> Map 으로 형변환
	 * 
	 * */
	public static Map<String, Object> objectToMap(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(obj);
        return objectMapper.readValue(jsonString, Map.class);
    }
	
	
	/**
	 * Map 으로 받은 데이터 convert 해서 보내기
	 * */
	public Map<String, String> xssSanitizer(Map<String, Object> objectMap) {
		
		Map<String, String> convertedMap = new HashMap<>();
		
		for(String key : objectMap.keySet()) {
			String value = (String) objectMap.get(key);
			String convertedData = convertData(value);
			convertedMap.put(key, convertedData);
		}
		
		
		return convertedMap;
	}
	
	
	/**
	 * XSS 태그 처리
	 * */
	public String convertData(String data) {
		
		if(StringUtils.isBlank(data)) {
			return data;
		}
		
		// 변환할 HTML 태그들
		Map<String, String> converterMap = new HashMap<>();
		converterMap.put("&", "&amp;");
		converterMap.put("<", "&lt;");
		converterMap.put(">", "&gt;");
		converterMap.put("\\(", "&#40;");
		converterMap.put("\\)", "&#41;");
		converterMap.put("#", "&#35;");
		converterMap.put("&", "&#38;");
		converterMap.put("'", "&#39;");
		converterMap.put(" ", "&nbsp;");
		converterMap.put("=", "&#61;");
		converterMap.put("[\\\\\\\"\\\\\\'][\\\\s]*javascript:(.*)[\\\\\\\"\\\\\\']", "\"\"");
		converterMap.put("script", "");
		converterMap.put("eval\\((.*)\\)", "");
		
		for(Map.Entry<String, String> entry : converterMap.entrySet()) {
			data = data.replaceAll(entry.getKey(), entry.getValue());
		}
		
		return data;
	}
	
}
