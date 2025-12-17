package com.schoolar.sb.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleException( Exception e ) {

        if ( e instanceof MethodArgumentNotValidException me ) {
            return ResponseEntity.badRequest().body( me.getBindingResult().getFieldError().getDefaultMessage() );
        }

        return ResponseEntity.internalServerError().body( "An error occurred " + e.getMessage() );
    }

    @ExceptionHandler
    public ResponseEntity<String> handlePersonException( PersonException e ) {
        return ResponseEntity.badRequest().body( "Person exception occurred: " + e.getMessage() );
    }
}
