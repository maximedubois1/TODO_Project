//package com.sp.service.impl;
//
//import com.sp.model.dto.CardDTO;
//import com.sp.model.dto.UserDTO;
//import com.sp.service.CardService;
//import com.sp.service.MarketService;
//import com.sp.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.*;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@Service
//public class MarketServiceImpl implements MarketService {
//
//
//    private final CardService cardService;
//    private final UserService userService;
//
//    public MarketServiceImpl(CardService cardService, UserService userService) {
//        this.cardService = cardService;
//        this.userService = userService;
//    }
//
//
//    @Override
//    public int sell(Long userId, Long cardId) {
//
//        /*
//            Manage the adding to user wallet
//         */
//        Optional<UserDTO> user = this.userService.getById(userId);
//        if (user.isEmpty()) return 1;
//        UserDTO updatedUser = user.get();
///*
//            Manage the Selling of card
//         */
//
//        Optional<CardDTO> cardToSell  = this.cardService.getById(cardId);
//        if (cardToSell.isEmpty()) return 1;
//        CardDTO cardSell = cardToSell.get();
//
//        if(Objects.equals(cardSell.getUser(), updatedUser)) return 1;
//
//
//
//        int price = cardToSell.get().getPrice();
//
//        updatedUser.setWallet(updatedUser.getWallet() + price);
//        this.userService.update(updatedUser);
//
//        this.cardService.setOwner(null,cardSell);
//
//
//        return 0;
//    }
//
//    @Override
//    public int buy(Long userId, Long cardId) {
//        /*
//            The card is in the market ?
//         */
//
//        Optional<CardDTO> cardToSell  = this.cardService.getById(cardId);
//        if (cardToSell.isEmpty()) return 1;
//        CardDTO cardSell = cardToSell.get();
//        if(cardSell.getUser() != null) return 1;
//
//        /*
//            The user have the money ?
//         */
//        int price = cardToSell.get().getPrice();
//        Optional<UserDTO> user = userService.getById(userId);
//        if (user.isEmpty()) return 1;
//        UserDTO updatedUser = user.get();
//        if(updatedUser.getWallet() < price) return 1;
//
//        /*
//            Make the transaction
//         */
//
//        updatedUser.setWallet(updatedUser.getWallet() - price);
//        userService.update(updatedUser);
//        this.cardService.setOwner(userId,cardSell);
//        return 0;
//
//    }
//}
