package com.fun.springdatadvdrental.exception;

/**
 * Thrown when an entity cannot be found with a given ID.
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, Long id) {
        super(entity + " with ID " + id + " not found");
    }
}
