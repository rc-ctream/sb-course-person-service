package com.schoolar.sb.exception;

import org.springframework.http.HttpStatus;

public class PersonAlreadyExistsException extends RuntimeException {

    public PersonAlreadyExistsException( String message ) {
        super( message );
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
