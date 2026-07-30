package com.example.cardgame.Constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CardGameConstants {
    public static final int HAND_SIZE = 10;
    public static final String API_BASE_PATH = "/api";
    public static final String RANDOM_HAND_ENDPOINT = "/random-hand-cards";
}