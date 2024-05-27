package com.sp.mapper;

import com.sp.model.Card;
import com.sp.model.dto.CardDTO;
import com.sp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardMapper {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public CardMapper(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public CardDTO toDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId());
        cardDTO.setName(card.getName());
        cardDTO.setDescription(card.getDescription());
        cardDTO.setImageUrl(card.getImageUrl());
        cardDTO.setFamily(card.getFamily());
        cardDTO.setAffinity(card.getAffinity());
        cardDTO.setHp(card.getHp());
        cardDTO.setEnergy(card.getEnergy());
        cardDTO.setAttack(card.getAttack());
        cardDTO.setDefense(card.getDefense());
        cardDTO.setPrice(card.getPrice());

        if (card.getUser() != null) {
            cardDTO.setUser(userMapper.toDTO(userRepository.findById(card.getUser().getId()).orElseThrow()));
        }

        return cardDTO;
    }

    public Card toEntity(CardDTO cardDTO) {
        Card card = new Card();
        card.setId(cardDTO.getId());
        card.setName(cardDTO.getName());
        card.setDescription(cardDTO.getDescription());
        card.setImageUrl(cardDTO.getImageUrl());
        card.setFamily(cardDTO.getFamily());
        card.setAffinity(cardDTO.getAffinity());
        card.setHp(cardDTO.getHp());
        card.setEnergy(cardDTO.getEnergy());
        card.setAttack(cardDTO.getAttack());
        card.setDefense(cardDTO.getDefense());
        card.setPrice(cardDTO.getPrice());

        if (cardDTO.getUser() != null) {
            card.setUser(userRepository.findById(cardDTO.getUser().getId()).orElseThrow());
        }

        return card;
    }

    public List<CardDTO> toDTOs(List<Card> cardList) {
        List<CardDTO> cardDTOList = new ArrayList<>();
        for (Card card : cardList) {
            cardDTOList.add(toDTO(card));
        }
        return cardDTOList;
    }

    public List<Card> toEntities(List<CardDTO> cardDTOList) {
        List<Card> cardList = new ArrayList<>();
        for (CardDTO cardDTO : cardDTOList) {
            cardList.add(toEntity(cardDTO));
        }
        return cardList;
    }
}
