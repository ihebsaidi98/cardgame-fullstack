package com.example.cardgame.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Les 4 couleurs d'un jeu de cartes.
 * L'ordre d'énumération définit l'ordre de tri.
 */
@Getter
@AllArgsConstructor
@ToString
public enum Suit {
    CLUBS("Trèfle"),
    DIAMONDS("Carreau"),
    HEARTS("Cœur"),
    SPADES("Pique");

    private final String displayName;
}