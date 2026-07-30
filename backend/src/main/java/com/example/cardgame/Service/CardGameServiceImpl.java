package com.example.cardgame.Service;

import com.example.cardgame.Constant.CardGameConstants;
import com.example.cardgame.Dto.Mapper.CardMapper;
import com.example.cardgame.Dto.Response.HandResponseDTO;
import com.example.cardgame.Exception.BusinessException;
import com.example.cardgame.Interface.CardGameService;
import com.example.cardgame.Model.Card;
import com.example.cardgame.Model.Rank;
import com.example.cardgame.Model.Suit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/** Implémentation du service : toute la logique métier. */
@Service @Slf4j @RequiredArgsConstructor
public class CardGameServiceImpl implements CardGameService {

    private final CardMapper cardMapper;

    private final AtomicLong totalCalls = new AtomicLong(0);
    private final AtomicLong totalCardsDealt = new AtomicLong(0);

    @Override
    public HandResponseDTO generateRandomHand() {
        totalCalls.incrementAndGet();
        totalCardsDealt.addAndGet(CardGameConstants.HAND_SIZE);
        log.info("Génération de la main aléatoire");
        try {
            // 1. Paquet complet
            List<Card> fullPack = createFullPack();
            // 2. Mélange
            List<Card> shuffled = shuffle(fullPack);
            // 3. Tirage de 10 cartes
            List<Card> randomHand = draw(shuffled, CardGameConstants.HAND_SIZE);
            // 4. Tri d’une copie
            List<Card> sortedHand = sortHand(randomHand);
            // 5. Conversion en DTO
            return HandResponseDTO.builder()
                    .fullPack(cardMapper.toDtoList(fullPack))
                    .randomHand(cardMapper.toDtoList(randomHand))
                    .sortedHand(cardMapper.toDtoList(sortedHand))
                    .build();
        } catch (Exception e) {
            log.error("Erreur : {}", e.getMessage());
            throw new BusinessException("Impossible de générer la main : " + e.getMessage());
        }
    }

    @Override
    public Map<String, Long> getStats() {
        return Map.of(
                "totalCalls", totalCalls.get(),
                "totalCardsDealt", totalCardsDealt.get()
        );
    }
    // --- Méthodes privées de logique ---

    private List<Card> createFullPack() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        return deck;
    }

    private List<Card> shuffle(List<Card> cards) {
        List<Card> shuffled = new ArrayList<>(cards);
        Collections.shuffle(shuffled);
        return shuffled;
    }

    private List<Card> draw(List<Card> pack, int size) {
        if (size > pack.size()) {
            throw new IllegalArgumentException(
                    "Taille demandée (" + size + ") > taille du paquet (" + pack.size() + ")"
            );
        }
        return pack.stream().limit(size).toList();
    }

    private List<Card> sortHand(List<Card> hand) {
        List<Card> sorted = new ArrayList<>(hand);
        sorted.sort(Comparator.comparing(Card::getSuit).thenComparing(Card::getRank));
        return sorted;
    }


}