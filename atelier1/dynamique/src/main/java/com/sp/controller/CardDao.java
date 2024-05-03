package com.sp.controller;

import com.sp.model.CardDTO;
import com.sp.utils.HttpUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardDao {
    private List<CardDTO> cardDTOList;
    private final String url = "http://tp.cpe.fr:8083/card";

    public CardDao() {
        cardDTOList = new ArrayList<>();
    }

    public String addCard(CardDTO cardDTO) throws IOException {

        System.out.println(cardDTO.toJson());
        return HttpUtils.sendPostRequest(url, cardDTO.toJson());
    }

    public List<CardDTO> getCardList() {

        return List.of();
    }
}
