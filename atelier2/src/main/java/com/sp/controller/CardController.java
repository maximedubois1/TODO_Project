package com.sp.controller;

import com.sp.model.dto.CardDTO;
import com.sp.model.dto.UserDTO;
import com.sp.service.AuthService;
import com.sp.service.CardService;
import com.sp.service.MarketService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService cardService;
    private final AuthService authService;
    private final MarketService marketService;

    public CardController(CardService cardService, AuthService authService, MarketService marketService) {
        this.cardService = cardService;
        this.authService = authService;
        this.marketService = marketService;
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

    @GetMapping("/{id}/sell")
    public ResponseEntity<CardDTO> sellCardsToMarket(HttpServletRequest request, @PathVariable long id) {
        UserDTO user = this.authService.getLoggedUser(request);
        return switch (this.marketService.sell(user.getId(), id)) {
            case 0 -> ResponseEntity.ok(this.cardService.getById(id).orElse(null));
            case 1 -> ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            case 2 -> ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        };
    }

    @GetMapping("/{id}/buy")
    public ResponseEntity<CardDTO> buyCardsToMarket(HttpServletRequest request, @PathVariable Long id) {
        UserDTO user = this.authService.getLoggedUser(request);
        return switch (this.marketService.buy(user.getId(), id)) {
            case 0 -> ResponseEntity.ok(this.cardService.getById(id).orElse(null));
            case 1 -> ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            case 2 -> ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        };
    }
    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> getCardById(HttpServletRequest request, @PathVariable Long id) {
        UserDTO user = this.authService.getLoggedUser(request);
        if (user.getCards().stream().anyMatch(card -> card.getId().equals(id))) {
            return ResponseEntity.ok(this.cardService.getById(id).orElse(null));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }}

}
