package com.example.cardgame.Dto.Response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

/** Réponse contenant les trois listes. */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class HandResponseDTO {
    private List<CardResponseDTO> fullPack;
    private List<CardResponseDTO> randomHand;
    private List<CardResponseDTO> sortedHand;
}