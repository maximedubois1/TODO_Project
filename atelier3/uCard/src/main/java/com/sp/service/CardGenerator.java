package com.sp.service;

import com.sp.model.Card;

/**
 * This interface defines the operation of generating a new Card.
 */
public interface CardGenerator {

    /**
     * Generates a new card.
     *
     * @return a Card object.
     */
    Card generateNewCardForUser(Long userId);
}
