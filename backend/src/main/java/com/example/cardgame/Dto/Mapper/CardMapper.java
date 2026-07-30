package com.example.cardgame.Dto.Mapper;

import com.example.cardgame.Dto.Response.CardResponseDTO;
import com.example.cardgame.Model.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

/** Convertit Card ↔ CardResponseDTO. */
@Component @RequiredArgsConstructor @Slf4j
public class CardMapper {

    public CardResponseDTO toDto(Card card) {
        if (card == null) return null;
        return CardResponseDTO.builder()
                .suit(card.getSuit().getDisplayName())
                .rank(card.getRank().getDisplayName())
                .displayName(card.toString())
                .imageName(card.getImageName())
                .build();
    }

    public List<CardResponseDTO> toDtoList(List<Card> cards) {
        return cards.stream().map(this::toDto).toList();
    }
}