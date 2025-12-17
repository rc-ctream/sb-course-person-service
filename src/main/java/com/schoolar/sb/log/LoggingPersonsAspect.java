package com.schoolar.sb.log;

import com.schoolar.sb.exception.PersonException;
import com.schoolar.sb.persistent.Person;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingPersonsAspect {

    @Before( "execution(* com.schoolar.sb.persistent.PersonRepository.save(..))" )
    public void logBeforeSavePerson( JoinPoint joinPoint ) {
        var person = ( Person ) joinPoint.getArgs()[0];
        log.info( "AOP - Called save person method with username " + person.getName() );
    }

    @AfterReturning(
            pointcut = "execution(* com.schoolar.sb.persistent.PersonRepository.findByPersonId(..)) && args(personId)",
            returning = "person" )
    public void logAfterSavePerson( Integer personId, Object person ) {
        log.info( "AOP - Person with id " + personId + " was found" );
    }

    @AfterThrowing(
            pointcut = "execution(* com.schoolar.sb.persistent.PersonRepository.findByPersonId(..)) && args(personId)",
            throwing = "ex" )
    public void logAfterSavePerson( Integer personId, PersonException ex ) {
        log.info( "AOP - Exception occurred while trying to find person with id " + personId );
    }
}
