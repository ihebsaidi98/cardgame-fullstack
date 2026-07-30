package com.example.cardgame.Dto.Response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/** DTO d’une carte pour le client. */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CardResponseDTO {
    private String suit;        // nom français de la couleur
    private String rank;        // nom français de la valeur
    private String displayName; // "As de Pique"
    private String imageName;   // "ace_of_spades.png"
}