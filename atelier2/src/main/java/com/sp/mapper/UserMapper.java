package com.sp.mapper;

import com.sp.model.Card;
import com.sp.model.UserEntity;
import com.sp.model.dto.CardDTO;
import com.sp.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {

    public UserDTO toDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setWallet(user.getWallet());

        List<CardDTO> cardDTOList = new ArrayList<>();
        for (Card card : user.getCards()) {
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

            cardDTOList.add(cardDTO);
        }
        userDTO.setCards(cardDTOList);

        return userDTO;
    }

    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        if (userDTO.getId() != null)
            user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setWallet(userDTO.getWallet());

        List<Card> cardList = new ArrayList<>();
        for (CardDTO cardDTO : userDTO.getCards()) {
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
            if (cardDTO.getUser() != null)
                card.setUserId(cardDTO.getUser().getId());

            cardList.add(card);
        }
        user.setCards(cardList);

        return user;
    }

    public List<UserDTO> toDTOs(List<UserEntity> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity user : users) {
            userDTOs.add(toDTO(user));
        }
        return userDTOs;
    }

    public List<UserEntity> toEntities(List<UserDTO> userDTOs) {
        List<UserEntity> users = new ArrayList<>();
        for (UserDTO userDTO : userDTOs) {
            users.add(toEntity(userDTO));
        }
        return users;
    }
}
