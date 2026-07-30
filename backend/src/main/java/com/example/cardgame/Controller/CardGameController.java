package com.example.cardgame.Controller;

import com.example.cardgame.Constant.CardGameConstants;
import com.example.cardgame.Dto.Response.HandResponseDTO;
import com.example.cardgame.Interface.CardGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/** Contrôleur REST pour le jeu de cartes. */
@RestController @RequestMapping(CardGameConstants.API_BASE_PATH)
@RequiredArgsConstructor @Slf4j
@Tag(name = "Card Game", description = "API du jeu de cartes")
@Validated
public class CardGameController {

    private final CardGameService cardGameService;

    @Operation(summary = "Génère une main aléatoire de 10 cartes")
    @GetMapping(CardGameConstants.RANDOM_HAND_ENDPOINT)
    public ResponseEntity<HandResponseDTO> getRandomHand() {
        log.info("GET {}", CardGameConstants.RANDOM_HAND_ENDPOINT);
        return ResponseEntity.ok(cardGameService.generateRandomHand());
    }
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        log.info("GET /stats");
        return ResponseEntity.ok(cardGameService.getStats());
    }
}