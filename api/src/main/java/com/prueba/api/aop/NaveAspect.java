package com.prueba.api.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NaveAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.prueba.api.services.INaveService.findById(..))")
    private void pointCut(){}

    @Before("pointCut()")
    public void loggerBefore (JoinPoint joinPoint){

        String args = Arrays.toString(joinPoint.getArgs()).replace("[", "").replace("]", "");

        if( Integer.parseInt(args) < 0 ){
            logger.info("Id de la nave no puede ser negativo: "+args);
        }
        
    }

}
