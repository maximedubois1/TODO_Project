package com.sp.controller;

import com.sp.model.dto.CardDTO;
import com.sp.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<CardDTO>> getAll() {
        return ResponseEntity.ok(this.cardService.getAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<List<CardDTO>>> getMine(@PathVariable Long id) {
        return ResponseEntity.ok(this.cardService.getByUserId(id));
    }

    @GetMapping("/market")
    public ResponseEntity<List<CardDTO>> getCardsOnMarket() {
        return ResponseEntity.ok(this.cardService.getAvailableCards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CardDTO>> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok(this.cardService.getById(id));
    }

    @GetMapping("/is-sellable/{id}")
    public ResponseEntity<Boolean> isSellable(@PathVariable Long id) {
        return ResponseEntity.ok(this.cardService.hasOwner(id));
    }

    @GetMapping("/is-buyable/{id}")
    public ResponseEntity<Boolean> isBuyable(@PathVariable Long id) {
        return ResponseEntity.ok(this.cardService.isAvailableOnMarket(id));
    }

    @PostMapping("/buy/{cardId}/to-user/{userId}")
    public ResponseEntity<String> addCardToUser(@PathVariable Long cardId, @PathVariable Long userId) {
        this.cardService.setOwner(userId, cardId);
        return ResponseEntity.ok("Card added to user");
    }

    @PostMapping("/sell/{cardId}")
    public ResponseEntity<String> removeCardFromUser(@PathVariable Long cardId) {
        this.cardService.setOwner(null, cardId);
        return ResponseEntity.ok("Card removed from user");
    }

    @PostMapping("/generate-for/{userId}")
    public ResponseEntity<List<CardDTO>> generateFiveCardsForUser(@PathVariable Long userId) {
        List<CardDTO> cards = this.cardService.generateFiveCards(userId);
        for (CardDTO card : cards) {
            this.cardService.setOwner(userId, card.getId());
        }
        return ResponseEntity.ok(cards);
    }

}
