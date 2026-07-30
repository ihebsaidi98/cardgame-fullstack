package com.example.cardgame.Exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

/** Exception 404. */
@EqualsAndHashCode(callSuper = true)
@Value
public class ResourceNotFoundException extends RuntimeException {
    String message;
    public ResourceNotFoundException(String message) { super(message); this.message = message; }
    public ResourceNotFoundException(String message, Throwable cause) { super(message, cause); this.message = message; }
}