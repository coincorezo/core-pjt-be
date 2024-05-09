package com.pjt.core.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class XssFilterWrapper extends HttpServletRequestWrapper {

	

	public XssFilterWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = escape(values[i]);
        }
        return encodedValues;
    }
	
	 @Override
	    public String getParameter(String parameter) {
	        String value = super.getParameter(parameter);
	        return escape(value);
	    }

	
	
	private String escape(String value) {
		return StringEscapeUtils.escapeHtml4(value);
	}
	
	
}
