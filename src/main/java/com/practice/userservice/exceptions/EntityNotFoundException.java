package com.practice.userservice.exceptions;

public class EntityNotFoundException extends Throwable {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
