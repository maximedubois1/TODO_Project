package com.sp.utils;

import com.sp.model.dto.CardDTO;

public class TestsUtils {

    public static CardDTO createCardDto(String name, String description, String imageUrl, String family, String affinity, int hp, int energy, int attack, int defense, int price, Long userId) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setName(name);
        cardDTO.setDescription(description);
        cardDTO.setImageUrl(imageUrl);
        cardDTO.setFamily(family);
        cardDTO.setAffinity(affinity);
        cardDTO.setHp(hp);
        cardDTO.setEnergy(energy);
        cardDTO.setAttack(attack);
        cardDTO.setDefense(defense);
        cardDTO.setPrice(price);
        cardDTO.setUserId(userId);
        return cardDTO;
    }


}
