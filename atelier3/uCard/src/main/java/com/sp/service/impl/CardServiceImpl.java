package com.sp.service.impl;

import com.sp.mapper.CardMapper;
import com.sp.model.Card;
import com.sp.model.dto.CardDTO;
import com.sp.repository.CardRepository;
import com.sp.service.CardGenerator;
import com.sp.service.CardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final CardGenerator cardGenerator;

    public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper, CardGenerator cardGenerator) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
        this.cardGenerator = cardGenerator;
    }

    @Override
    public List<CardDTO> getAll() {
        List<Card> cards = this.cardRepository.findAll();
        return cardMapper.toDTOs(cards);
    }

    @Override
    public Optional<List<CardDTO>> getByUserId(Long userId) {
        List<CardDTO> cards = this.cardRepository.findAllByUserId(userId)
                .stream()
                .map(cardMapper::toDTO)
                .toList();
        return Optional.of(cards);
    }

    // find cards that doesn't belong to any user
    @Override
    public List<CardDTO> getAvailableCards() {
        List<Card> cards = this.cardRepository.findAllWhereUserIdIsNull();
        return cardMapper.toDTOs(cards);
    }

    @Override
    public Optional<CardDTO> getById(Long id) {
        return this.cardRepository.findById(id)
                .map(this.cardMapper::toDTO);
    }

    @Override
    public List<CardDTO> generateFiveCards(Long userId) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Card card = this.cardGenerator.generateNewCardForUser(userId);
            this.cardRepository.save(card);
            cards.add(card);
        }
        return cardMapper.toDTOs(cards);
    }

    @Override
    public void setOwner(Long userId, Long cardId) {
        Card card = this.cardRepository.findById(cardId).orElseThrow();
        card.setUserId(userId);
        this.cardRepository.save(card);
    }

    @Override
    public boolean isAvailableOnMarket(Long id) {
        return this.cardRepository.findById(id).orElseThrow().getUserId() == null;
    }

    @Override
    public boolean hasOwner(Long id) {
        return this.cardRepository.findById(id).orElseThrow().getUserId() != null;
    }
}
