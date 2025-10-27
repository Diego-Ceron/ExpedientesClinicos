package com.expedientesclinicos.exception;

public class ResourceNotFoundException extends DomainException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
