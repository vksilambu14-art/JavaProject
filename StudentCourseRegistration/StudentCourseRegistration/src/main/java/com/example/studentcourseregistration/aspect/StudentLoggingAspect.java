package com.example.studentcourseregistration.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudentLoggingAspect {
	@Around("execution(* com.example.studentcourseregistration.service.StudentService.*(..))")
	public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("Method Started: " + methodName);
		long startTime = System.currentTimeMillis();
		try {
			Object result = joinPoint.proceed();
			return result;
		} finally {
			long endTime = System.currentTimeMillis();
			System.out.println("Method Completed: " + methodName);
			System.out.println("Execution Time: " + (endTime - startTime) + " ms");
		}
	}

}
