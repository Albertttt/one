package com.example.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("within(com.example.demo.GreetingController)")
    public void stringProcessingMethods() {
    }

    @Before("stringProcessingMethods()")
    public void logMethodInputParam(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature()
                .getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.log(Level.INFO, "название метода: " + methodName+": входящие параметры: "+args);
    }

    @AfterReturning(pointcut = "execution(public * com.example.demo.GreetingController.*(..))", returning = "result")
    public void logMethodOutputParam(JoinPoint joinPoint, Object result){
        logger.log(Level.INFO, "возвращенное значение: " + result.getClass().getName()+": "+ ((Greeting) result).getId()+"; "+((Greeting) result).getContent());
    }
}
