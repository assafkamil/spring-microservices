package com.geekoosh.edu.cloud.vacations.users.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServicesAspects {
    @AfterThrowing(pointcut = "execution(* com.geekoosh.edu.cloud.vacations.users.services.*.*(..))", throwing ="ex")
    public void exceptionAspect(JoinPoint jp, Exception ex) {
        Class clz = jp.getTarget().getClass();
        Logger logger = LoggerFactory.getLogger(clz);
        Marker marker = MarkerFactory.getMarker("DB");
        marker.add(MarkerFactory.getMarker("EXCEPTION"));
        marker.add(MarkerFactory.getMarker("INTEGRITY"));

        logger.error(marker, "DB Error", ex);
    }
}
