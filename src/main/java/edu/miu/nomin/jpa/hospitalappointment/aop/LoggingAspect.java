package edu.miu.nomin.jpa.hospitalappointment.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* edu.miu.nomin.jpa.hospitalappointment.service.DoctorService.*(..))")
    public void doctorServiceMethods() {}

    @Before("doctorServiceMethods()")
    public void logBefore() {
        System.out.println("Logging before the doctor service method.");
    }

    @After("doctorServiceMethods()")
    public void logAfter() {
        System.out.println("Logging after the doctor service method.");
    }
}
