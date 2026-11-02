
package com.example.EmployeeLeaveManagement.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LeaveLoggingAspect {

    @Around("execution(* com.example.EmployeeLeaveManagement.service.EmployeeLeaveService.*(..))")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Method Started: "+ joinPoint.getSignature().getName());

        try {
            Object result = joinPoint.proceed();

            System.out.println("Method Completed: "+ joinPoint.getSignature().getName());

            return result;

        } catch (Exception e) {

            System.out.println("Method Failed: "+ joinPoint.getSignature().getName());

            throw e;
        }
    }
}
