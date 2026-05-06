package com.pictet.complet.taskservice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    // Cette ligne dit : "Exécute ce code AVANT chaque méthode du TaskController"
    @Before("@annotation(com.pictet.complet.taskservice.aspects.LogMe)")
    public void logAvecAnnotation(JoinPoint joinPoint) {
        System.out.println("✅ AOP avec Annotation sur : " + joinPoint.getSignature().getName());
    }
}
