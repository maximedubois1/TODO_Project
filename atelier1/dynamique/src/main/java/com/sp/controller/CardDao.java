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
    private final String url = "http://tp.cpe.fr:8083";

    public CardDao() {
        cardDTOList = new ArrayList<>();
    }

    public CardDTO addCard(CardDTO cardDTO) throws IOException {

        System.out.println(cardDTO.toJson());
        return CardDTO.fromJson(HttpUtils.sendPostRequest(url + "/card", cardDTO.toJson()));
    }

    public CardDTO getCardList() throws IOException {
        return CardDTO.fromJson(HttpUtils.sendGetRequest(url + "/cards"));
    }

    public CardDTO getCardById(int id) throws IOException {
        return CardDTO.fromJson(HttpUtils.sendGetRequest(url + "/card/" + id));
    }

    public void deleteCard(int id) throws IOException {
        HttpUtils.sendDeleteRequest(url + "/card/" + id);
    }
}
