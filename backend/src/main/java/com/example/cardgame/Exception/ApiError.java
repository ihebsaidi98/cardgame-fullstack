package com.example.cardgame.Exception;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/** Structure d’erreur. */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ApiError {
    private int status;
    private String message;
    private long timestamp;
}