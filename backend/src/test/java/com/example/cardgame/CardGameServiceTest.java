package com.example.cardgame;

import com.example.cardgame.Dto.Mapper.CardMapper;
import com.example.cardgame.Dto.Response.HandResponseDTO;
import com.example.cardgame.Exception.BusinessException;
import com.example.cardgame.Model.Card;
import com.example.cardgame.Service.CardGameServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardGameServiceTest {

    @Mock
    private CardMapper cardMapper;

    @InjectMocks
    private CardGameServiceImpl service;

    // ============================================================
    // TEST 1 : Vérifier la structure de la réponse (tailles)
    // ============================================================
    @Test
    void shouldGenerateHandWithCorrectStructure() {
        // Le mapper retourne la liste qu'il reçoit (comportement réaliste)
        when(cardMapper.toDtoList(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        HandResponseDTO response = service.generateRandomHand();

        assertNotNull(response);
        assertEquals(52, response.getFullPack().size(), "Paquet complet doit contenir 52 cartes");
        assertEquals(10, response.getRandomHand().size(), "Main aléatoire doit contenir 10 cartes");
        assertEquals(10, response.getSortedHand().size(), "Main triée doit contenir 10 cartes");
        verify(cardMapper, times(3)).toDtoList(anyList());
    }

    // ============================================================
    // TEST 2 : Vérifier l'ordre de tri (couleur puis valeur)
    // ============================================================
    @Test
    void shouldSortHandBySuitThenRank() {
        when(cardMapper.toDtoList(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        service.generateRandomHand();

        ArgumentCaptor<List<Card>> captor = ArgumentCaptor.forClass(List.class);
        verify(cardMapper, times(3)).toDtoList(captor.capture());

        List<Card> sortedHand = captor.getAllValues().get(2); // 3ème appel

        for (int i = 0; i < sortedHand.size() - 1; i++) {
            Card current = sortedHand.get(i);
            Card next = sortedHand.get(i + 1);
            int suitCompare = current.getSuit().compareTo(next.getSuit());
            if (suitCompare == 0) {
                assertTrue(current.getRank().compareTo(next.getRank()) <= 0,
                        "Même couleur : valeurs triées");
            } else {
                assertTrue(suitCompare < 0,
                        "Couleurs triées : " + current.getSuit() + " avant " + next.getSuit());
            }
        }
    }

    // ============================================================
    // TEST 3 : Gestion des exceptions (BusinessException)
    // ============================================================
    @Test
    void shouldWrapExceptionInBusinessException() {
        when(cardMapper.toDtoList(anyList())).thenThrow(new RuntimeException("Erreur simulée"));

        BusinessException exception = assertThrows(BusinessException.class,
                () -> service.generateRandomHand());
        assertTrue(exception.getMessage().contains("Impossible de générer la main"));
    }

    // ============================================================
    // TEST 4 : La main aléatoire est différente de la main triée
    // ============================================================
    @Test
    void randomHandShouldBeDifferentFromSortedHand() {
        when(cardMapper.toDtoList(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        service.generateRandomHand();

        ArgumentCaptor<List<Card>> captor = ArgumentCaptor.forClass(List.class);
        verify(cardMapper, times(3)).toDtoList(captor.capture());

        List<Card> randomHand = captor.getAllValues().get(1);
        List<Card> sortedHand = captor.getAllValues().get(2);

        boolean areDifferent = false;
        for (int i = 0; i < Math.min(randomHand.size(), sortedHand.size()); i++) {
            if (!randomHand.get(i).equals(sortedHand.get(i))) {
                areDifferent = true;
                break;
            }
        }
        assertTrue(areDifferent, "Les deux mains doivent avoir un ordre différent");
    }

    // ============================================================
    // TEST 5 : Tirage exactement 10 cartes
    // ============================================================
    @Test
    void shouldDrawExactlyTenCards() {
        when(cardMapper.toDtoList(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        HandResponseDTO response = service.generateRandomHand();

        assertEquals(10, response.getRandomHand().size(), "Main aléatoire doit contenir 10 cartes");
        assertEquals(10, response.getSortedHand().size(), "Main triée doit contenir 10 cartes");
    }

    // ============================================================
    // TEST 6 : La main triée est une copie (pas la même référence)
    // ============================================================
    @Test
    void sortedHandShouldBeDifferentInstance() {
        when(cardMapper.toDtoList(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        service.generateRandomHand();

        ArgumentCaptor<List<Card>> captor = ArgumentCaptor.forClass(List.class);
        verify(cardMapper, times(3)).toDtoList(captor.capture());

        List<Card> randomHand = captor.getAllValues().get(1);
        List<Card> sortedHand = captor.getAllValues().get(2);

        assertNotSame(randomHand, sortedHand, "Les listes doivent être des objets différents");
    }
}