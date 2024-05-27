package com.sp.controller;

import com.sp.controller.CardController;
import com.sp.model.dto.CardDTO;
import com.sp.model.dto.UserDTO;
import com.sp.service.AuthService;
import com.sp.service.CardService;
import com.sp.service.MarketService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private CardService cardService;

    @MockBean
    private MarketService marketService;



    @Test
    void buyCardSuccess() throws Exception {
        UserDTO user = new UserDTO();
        CardDTO card = new CardDTO();
        when(authService.getLoggedUser(null)).thenReturn(user);
        when(marketService.buy(user.getId(), 1L)).thenReturn(0);
        when(cardService.getById(1L)).thenReturn(Optional.of(card));

        mockMvc.perform(get("/api/v1/cards/1/buy")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void buyCardNotFound() throws Exception {
        UserDTO user = new UserDTO();
        when(authService.getLoggedUser(null)).thenReturn(user);
        when(marketService.buy(user.getId(), 1L)).thenReturn(1);

        mockMvc.perform(get("/api/v1/cards/1/buy")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void buyCardForbidden() throws Exception {
        UserDTO user = new UserDTO();
        when(authService.getLoggedUser(null)).thenReturn(user);
        when(marketService.buy(user.getId(), 1L)).thenReturn(2);

        mockMvc.perform(get("/api/v1/cards/1/buy")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void buyCardInternalServerError() throws Exception {
        UserDTO user = new UserDTO();
        when(authService.getLoggedUser(null)).thenReturn(user);
        when(marketService.buy(user.getId(), 1L)).thenReturn(3);

        mockMvc.perform(get("/api/v1/cards/1/buy")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void buyCardsToMarketSuccess() throws Exception {
        UserDTO user = new UserDTO();
        CardDTO card = new CardDTO();
        when(authService.getLoggedUser(null)).thenReturn(user);
        when(marketService.buy(user.getId(), 1L)).thenReturn(0);
        when(cardService.getById(1L)).thenReturn(Optional.of(card));

        mockMvc.perform(get("/api/v1/cards/1/buy")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void buyCardsToMarketNotFound() throws Exception {
        UserDTO user = new UserDTO();
        when(authService.getLoggedUser(null)).thenReturn(user);
        when(marketService.buy(user.getId(), 1L)).thenReturn(1);

        mockMvc.perform(get("/api/v1/cards/1/buy")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void buyCardsToMarketForbidden() throws Exception {
        UserDTO user = new UserDTO();
        when(authService.getLoggedUser(null)).thenReturn(user);
        when(marketService.buy(user.getId(), 1L)).thenReturn(2);

        mockMvc.perform(get("/api/v1/cards/1/buy")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void buyCardsToMarketInternalServerError() throws Exception {
        UserDTO user = new UserDTO();
        when(authService.getLoggedUser(null)).thenReturn(user);
        when(marketService.buy(user.getId(), 1L)).thenReturn(3);

        mockMvc.perform(get("/api/v1/cards/1/buy")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
