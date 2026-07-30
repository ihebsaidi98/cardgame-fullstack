package com.example.cardgame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CardGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnHandWithDefaultSize() throws Exception {
        mockMvc.perform(get("/api/random-hand-cards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullPack.length()").value(52))
                .andExpect(jsonPath("$.randomHand.length()").value(10))
                .andExpect(jsonPath("$.sortedHand.length()").value(10));
    }
}