package com.pjt.core.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


//@JsonComponent
//@Component
public class XssSerializer extends StdSerializer<String> implements ContextualSerializer {
	

	protected XssSerializer() {
		super(String.class);
	}
		
	@Override
	public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		String serializedData = this.convertData(value);
		gen.writeString(serializedData);
	}
	
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



	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
			throws JsonMappingException {
		return new XssSerializer();
	}












	
}
