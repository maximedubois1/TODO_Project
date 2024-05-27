package com.sp.service;

public interface MarketService {
    /**
     * Sells a card to the market.
     *
     * @param userId the ID of the user who is selling the card.
     * @param cardId the ID of the card being sold.
     * @return 0 if the card was successfully sold, 1 if the card does not exist, 2 if the user does not own the card.
     */
    int sell(Long userId, Long cardId);

    /**
     * Buys a card from the market.
     *
     * @param userId the ID of the user who is buying the card.
     * @param cardId the ID of the card being bought.
     * @return 0 if the card was successfully bought, 1 if the card does not exist, 2 if the user does not have enough money to buy the card.
     */
    int buy(Long userId, Long cardId);
}
