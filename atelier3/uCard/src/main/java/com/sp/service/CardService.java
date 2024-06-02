package com.sp.service;

import com.sp.model.dto.CardDTO;
import jakarta.servlet.http.Cookie;

import java.io.IOException;
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
     * Fetches the cards that belong to a specific user for a fight.
     * @param userId
     * @return
     */
    Optional<List<CardDTO>> getByUserIdForFight(Long userId);

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
     * Generates five cards.
     *
     * @param userId the ID of the user who will own the cards.
     * @return a list of five CardDTO objects.
     */
    List<CardDTO> generateFiveCards(Long userId);

    /**
     * Sets the owner of a card.
     *
     * @param userId  the ID of the user who will be the owner of the card.
     * @param cardId the ID of the card.
     */
    void setOwner(Long userId, Long cardId);

    /**
     * Checks if a card is available on the marketplace.
     *
     * @param id the ID of the card.
     * @return true if the card is available on the marketplace, false otherwise.
     */
    boolean isAvailableOnMarket(Long id);

    /**
     * Checks if a card has an owner.
     *
     * @param id the ID of the card.
     * @return true if the card has an owner, false otherwise.
     */
    boolean hasOwner(Long id);

    /**
     * Updates a card.
     *
     * @param cardDTO the CardDTO object containing the updated information.
     * @return the updated CardDTO object.
     */
    CardDTO update(CardDTO cardDTO);

    /**
     * Buys a card.
     *
     * @param userId the ID of the user who will buy the card.
     * @param cardId the ID of the card.
     */
    void buyCard(Long userId, Long cardId, Cookie cookie) throws IOException;

    /**
     * Sells a card.
     *
     * @param cardId the ID of the card.
     */
    void sellCard(Long cardId, Cookie cookie) throws IOException;
}
