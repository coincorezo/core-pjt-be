package com.pjt.core.common.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class TestAop {

	@Pointcut("execution(* com.pjt.core..*.*(..))")
	private void cut() {}
	
	@Before("execution(* com.pjt.core..*.*(..))")
	public void before(JoinPoint joinPoint) {
		joinPoint.getArgs(); // 배열로 넘어옴
	}
	
}
