package com.example.cardgame.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Carte à jouer (immuable).
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public final class Card {
    private final Suit suit;
    private final Rank rank;

    public String getImageName() {
        return rank.getImageLabel() + "_of_" + suit.name().toLowerCase() + ".png";
    }

    @Override
    public String toString() {
        return rank.getDisplayName() + " de " + suit.getDisplayName();
    }
}