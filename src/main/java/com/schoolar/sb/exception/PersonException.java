package com.schoolar.sb.exception;

import org.springframework.http.HttpStatus;

public class PersonException extends RuntimeException {

    public PersonException( String message ) {
        super( message );
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
