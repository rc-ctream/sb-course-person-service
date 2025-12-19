package com.schoolar.sb.exception;

import org.springframework.http.HttpStatus;

public class DepartmentException extends RuntimeException {

    public DepartmentException(String message) {
        super(message);
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}