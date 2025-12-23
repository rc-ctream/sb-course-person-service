package com.schoolar.sb.log;

import com.schoolar.sb.exception.DepartmentException;
import com.schoolar.sb.persistent.entity.Department;
import com.schoolar.sb.persistent.entity.DepartmentType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Aspect
@Component
public class LogginDepartmentsAspect {

    @Before("execution(* com.schoolar.sb.persistent.repository.DepartmentRepository.save(..))")
    public void logBeforeSave(JoinPoint joinPoint) {
        var department = (Department) joinPoint.getArgs()[0];
        log.info( "AOP - Called save department method with department " + department.getType().name() );
    }

    @AfterReturning(
            value = "execution(* com.schoolar.sb.persistent.repository.DepartmentRepository.findByType(..)) && args(departmentType)",
            returning = "department")
    public void logAfterFindByType(DepartmentType departmentType, Optional<Department> department ) {
        if(department.isEmpty())
            log.info( "AOP - Could not find any department with type " + departmentType.name());
        else
            log.info( "AOP - Department with type " + departmentType.name() + " was found" );
    }

    @AfterThrowing(
            pointcut = "execution(* com.schoolar.sb.persistent.repository.DepartmentRepository.findByType(..)) && args(departmentType)",
            throwing = "ex" )
    public void logAfterFindByType(DepartmentType departmentType, DepartmentException ex ) {
        log.info( "AOP - Exception occurred while trying to find deparment by type " + departmentType );
    }
}
