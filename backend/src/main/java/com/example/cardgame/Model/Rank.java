package com.example.cardgame.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum Rank {
    TWO("2", 2, "2"),
    THREE("3", 3, "3"),
    FOUR("4", 4, "4"),
    FIVE("5", 5, "5"),
    SIX("6", 6, "6"),
    SEVEN("7", 7, "7"),
    EIGHT("8", 8, "8"),
    NINE("9", 9, "9"),
    TEN("10", 10, "10"),
    JACK("Jack", 11, "jack"),
    QUEEN("Queen", 12, "queen"),
    KING("King", 13, "king"),
    ACE("Ace", 14, "ace");

    private final String displayName;  // "2", "Jack", "Ace" (pour l'affichage)
    private final int value;           // 2, 3, ..., 14 (pour le tri)
    private final String imageLabel;   // "2", "10", "jack", "king" (pour le nom du fichier)


}