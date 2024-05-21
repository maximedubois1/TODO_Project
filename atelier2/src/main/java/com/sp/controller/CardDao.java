//package com.sp.controller;
//
//import com.sp.model.dto.CardDTO;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CardDao {
//    private final List<CardDTO> cardDTOList;
//
//    public CardDao() {
//        cardDTOList = new ArrayList<>();
//        createCardList();
//    }
//
//    private void createCardList() {
//        CardDTO card1 = new CardDTO("Card1", "Description1", "Family1", "Affinity1", "ImgUrl1", "SmallImgUrl1", 1, 1, 1, 1, 1, 1, 1);
//        CardDTO card2 = new CardDTO("Card2", "Description2", "Family2", "Affinity2", "ImgUrl2", "SmallImgUrl2", 2, 2, 2, 2, 2, 2, 2);
//        CardDTO card3 = new CardDTO("Card3", "Description3", "Family3", "Affinity3", "ImgUrl3", "SmallImgUrl3", 3, 3, 3, 3, 3, 3, 3);
//        CardDTO card4 = new CardDTO("Card4", "Description4", "Family4", "Affinity4", "ImgUrl4", "SmallImgUrl4", 4, 4, 4, 4, 4, 4, 4);
//
//        cardDTOList.add(card1);
//        cardDTOList.add(card2);
//        cardDTOList.add(card3);
//        cardDTOList.add(card4);
//    }
//
//    public CardDTO addCard(CardDTO cardDTO) {
//        cardDTO.setId(getLastId() + 1);
//        cardDTOList.add(cardDTO);
//        System.out.println("new card added, size is now: " + cardDTOList.size() + " cards.");
//        return cardDTO;
//    }
//
//    public List<CardDTO> getCardList() {
//        return this.cardDTOList;
//    }
//
//    public int getLastId() {
//        System.out.println("CardDTO: getLastId: " + (this.getCardList().size()));
//        return this.getCardList().size();
//    }
//
//    public CardDTO getCardById(int id) {
//        for (CardDTO cardDTO : cardDTOList) {
//            if (cardDTO.getId() == id) {
//                return cardDTO;
//            }
//        }
//        return null;
//    }
//
//    public void deleteCard(int id) {
//        cardDTOList.remove(id);
//    }
//}
