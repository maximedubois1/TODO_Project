package com.sp.controller;

import com.sp.model.dto.CardDTO;
import com.sp.model.dto.UserDTO;
import com.sp.service.AuthService;
import com.sp.service.CardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService cardService;
    private final AuthService authService;

    public CardController(CardService cardService, AuthService authService) {
        this.cardService = cardService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<CardDTO>> getAll() {
        return ResponseEntity.ok(this.cardService.getAll());
    }

    @GetMapping("/mine")
    public ResponseEntity<List<CardDTO>> getMine(HttpServletRequest request) {
        UserDTO user = this.authService.getLoggedUser(request);
        return ResponseEntity.ok(this.cardService.getByUserId(user.getId()).orElse(null));
    }

    @GetMapping("/market")
    public ResponseEntity<List<CardDTO>> getCardsOnMarket() {
        return ResponseEntity.ok(this.cardService.getAvailableCards());
    }

}
