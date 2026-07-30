package com.example.cardgame.Interface;

import com.example.cardgame.Dto.Response.HandResponseDTO;

import java.util.Map;

/** Interface du service métier. */
public interface CardGameService {
    HandResponseDTO generateRandomHand();
    Map<String, Long> getStats();

}