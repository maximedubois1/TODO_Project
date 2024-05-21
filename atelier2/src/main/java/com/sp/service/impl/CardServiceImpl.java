package com.sp.service.impl;

import com.sp.mapper.CardMapper;
import com.sp.model.Card;
import com.sp.model.dto.CardDTO;
import com.sp.model.dto.UserDTO;
import com.sp.repository.CardRepository;
import com.sp.service.CardGenerator;
import com.sp.service.CardService;
import com.sp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final UserService userService;
    private final CardGenerator cardGenerator;

    public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper, UserService userService, CardGenerator cardGenerator) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
        this.userService = userService;
        this.cardGenerator = cardGenerator;
    }

    @Override
    public List<CardDTO> getAll() {
        List<Card> cards = this.cardRepository.findAll();
        return cardMapper.toDTOs(cards);
    }

    @Override
    public Optional<List<CardDTO>> getByUserId(Long userId) {
        UserDTO user = userService.getById(userId).orElseThrow();
        List<CardDTO> cards = user.getCards();
        return Optional.of(cards);
    }

    // find cards that doesn't belong to any user
    @Override
    public List<CardDTO> getAvailableCards() {
        List<Card> cards = this.cardRepository.findAllByUserIdIsNull();
        return cardMapper.toDTOs(cards);
    }

    @Override
    public Optional<CardDTO> getById(Long id) {
        return this.cardRepository.findById(id)
                .map(this.cardMapper::toDTO);
    }

    @Override
    public void generateFiveCardsForUser(Long userId) {
        for (int i = 0; i < 5; i++) {
            Card card = this.cardGenerator.generateNewCard();
            card.setUserId(userId);
            this.cardRepository.save(card);
        }
    }

    @Override
    public void sell(Long id) {
        Card card = this.cardRepository.findById(id).orElseThrow();
        card.setUserId(null);
        this.cardRepository.save(card);
    }

    @Override
    public void buy(Long userId, Long cardId) {
        Card card = this.cardRepository.findById(cardId).orElseThrow();
        card.setUserId(userId);
        this.cardRepository.save(card);
    }
}
