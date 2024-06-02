package com.sp.controller;

import com.sp.model.dto.CardDTO;
import com.sp.model.dto.FightDTO;
import com.sp.service.CardService;
import com.sp.service.FightService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService cardService;
    private final FightService fightService;

    public CardController(CardService cardService, FightService fightService) {
        this.cardService = cardService;
        this.fightService = fightService;
    }

    @GetMapping
    public ResponseEntity<List<CardDTO>> getAll() {
        return ResponseEntity.ok(this.cardService.getAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<List<CardDTO>>> getMine(@PathVariable Long id) {
        return ResponseEntity.ok(this.cardService.getByUserId(id));
    }

    @GetMapping("/user/{id}/fight")
    public ResponseEntity<Optional<List<CardDTO>>> getMineForFight(@PathVariable Long id) {
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
    public ResponseEntity<String> addCardToUser(@PathVariable Long cardId, @PathVariable Long userId, HttpServletRequest request) {
        Cookie cookie = Arrays.stream(request.getCookies()).anyMatch(c -> c.getName().equals("auth_jwt")) ?
                Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("auth_jwt")).findFirst().get() : null;
        try {
            this.cardService.buyCard(userId, cardId, cookie);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Card added to user");
    }

    @PostMapping("/sell/{cardId}")
    public ResponseEntity<String> removeCardFromUser(@PathVariable Long cardId, HttpServletRequest request) {
        Cookie cookie = Arrays.stream(request.getCookies()).anyMatch(c -> c.getName().equals("auth_jwt")) ?
                Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("auth_jwt")).findFirst().get() : null;
        try {
            this.cardService.sellCard(cardId, cookie);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    @PostMapping("/fight")
    public ResponseEntity<Long> fight(@RequestBody FightDTO fightDTO) {
        Long winnerID = null;
        try {
            winnerID = this.fightService.fight(fightDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(winnerID);
    }


}
