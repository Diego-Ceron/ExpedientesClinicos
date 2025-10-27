package com.expedientesclinicos.exception;

public class AccessDeniedException extends DomainException {

    public AccessDeniedException(String message) {
        super(message);
    }
}
