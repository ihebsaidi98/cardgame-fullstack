package com.example.cardgame.Exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

/** Exception métier. */
@EqualsAndHashCode(callSuper = true)
@Value
public class BusinessException extends RuntimeException {
    String message;
    public BusinessException(String message) { super(message); this.message = message; }
    public BusinessException(String message, Throwable cause) { super(message, cause); this.message = message; }
}