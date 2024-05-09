package com.pjt.core.common.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class XssFilter implements Filter {

	private final List<String> includeUri = Arrays.asList("/board/*");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		// 요청 URI
		// String requestURI =  httpServletRequest.getRequestURI();
		
//		if(isAllowedUri(requestURI)) {
//			chain.doFilter(new XssFilterWrapper(request), response);
//			return;
//			
//			XssFilterWrapper wrapper = new XssFilterWrapper((HttpServletRequest) request);
//			chain.doFilter(wrapper, response);
//			
//		}
		
		
		XssFilterWrapper wrapper = new XssFilterWrapper((HttpServletRequest) request);
		chain.doFilter(wrapper, response);
	}
	
	private boolean isAllowedUri(String uri) {
		return includeUri.stream().anyMatch(url -> url.equals(uri));
	}
	
	
}
