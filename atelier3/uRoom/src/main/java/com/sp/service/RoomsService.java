package com.sp.service;

import com.sp.model.dto.RoomDTO;

import java.util.List;
import java.util.Optional;

/**
 * This interface defines the operations that can be performed on Cards.
 */
public interface RoomsService {

    /**
     * Fetches all the room.
     *
     * @return a list of all RoomDTO objects.
     */
    List<RoomDTO> getAll();

    /**
     * Fetches the room by name
     *
     * @param name the name of the room
     * @return an Optional containing the RoomDTO object if it exists, or an empty Optional if the room does not exist.
     */
    Optional<RoomDTO> getRoomByName(String name);

    /**
     * Creates a new room.
     *
     * @param roomDTO the room to create
     **/
     RoomDTO createRoom(RoomDTO roomDTO);


    /**
     * Deletes a room.
     *
     * @param name
     */
    void deleteRoom(String name);

    /**
     * Joins a room.
     *
     * @param roomName
     * @param userID
     */
    void joinRoom(String roomName, Long userID);


    /**
     * Set a card for a user in a room.
     * @param roomName
     * @param userID
     * @param cardID
     */
    void setCard(String roomName, Long userID, Long cardID);
    /**
     * Add OwnerCard
     *
     * @param roomName the name of the room
     * @param cardID the id of the card
     */
    void addOwnerCard(String roomName, Long cardID);

    /**
     * Add OpponentCard
     *
     * @param roomName the name of the room
     * @param cardID the id of the card
     */
    void addOpponentCard(String roomName, Long cardID);
}
