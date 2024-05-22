package com.sp.service;

import com.sp.model.dto.CardDTO;

import java.util.List;
import java.util.Optional;

/**
 * This interface defines the operations that can be performed on Cards.
 */
public interface CardService {

    /**
     * Fetches all the cards.
     *
     * @return a list of all CardDTO objects.
     */
    List<CardDTO> getAll();

    /**
     * Fetches the cards that belong to a specific user.
     *
     * @param userId the ID of the user.
     * @return an Optional containing a list of CardDTO objects that belong to the user, or an empty Optional if the user has no cards.
     */
    Optional<List<CardDTO>> getByUserId(Long userId);

    /**
     * Fetches the cards that are available on the marketplace.
     *
     * @return a list of CardDTO objects that are available on the marketplace.
     */
    List<CardDTO> getAvailableCards();

    /**
     * Fetches a card by its ID.
     *
     * @param id the ID of the card.
     * @return an Optional containing the CardDTO object if it exists, or an empty Optional if the card does not exist.
     */
    Optional<CardDTO> getById(Long id);

    /**
     * Generates five cards for a specific user.
     *
     * @param userId the ID of the user.
     */
    void generateFiveCardsForUser(Long userId);
    void setOwner(Long userId, CardDTO carddto);
}
