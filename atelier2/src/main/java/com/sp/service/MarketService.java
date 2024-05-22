package com.sp.service;

public interface MarketService {
    int sell(Long userId, Long cardId);
    int buy(Long userId, Long cardId);
}
