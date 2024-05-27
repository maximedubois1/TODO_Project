package com.sp.service;

import com.sp.model.dto.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * This interface defines the operations that can be performed on Users.
 */
public interface UserService {

    /**
     * Fetches all the users.
     *
     * @return a list of all UserDTO objects.
     */
    List<UserDTO> getAll();

    /**
     * Fetches a user by its ID.
     *
     * @param id the ID of the user.
     * @return an Optional containing the UserDTO object if it exists, or an empty Optional if the user does not exist.
     */
    Optional<UserDTO> getById(Long id);

    /**
     * Fetches a user by its surname.
     *
     * @param surname the surname of the user.
     * @return the UserDTO object if it exists.
     */
    UserDTO getBySurname(String surname);

    /**
     * Creates a new user.
     *
     * @param userDTO the UserDTO object containing the details of the user to be created.
     * @return an Optional containing the created UserDTO object if the creation was successful, or an empty Optional if the creation was unsuccessful.
     */
    Optional<UserDTO> create(UserDTO userDTO);

    /**
     * Updates a user.
     *
     * @param userDTO the UserDTO object containing the details of the user to be updated.
     * @return an Optional containing the updated UserDTO object if the update was successful, or an empty Optional if the update was unsuccessful.
     */
    Optional<UserDTO> update(UserDTO userDTO);

    /**
     * Tests the wallet of a user.
     * @param id the ID of the user.
     * @param amount the amount to test.
     * @return a boolean indicating whether the wallet has enough money.
     */
    boolean testWallet(long id, int amount);

    /**
     * Adds an amount to the wallet of a user.
     *
     * @param id the ID of the user.
     * @param amount the amount to add.
     * @return a boolean indicating whether the operation was successful.
     */
    boolean addWallet(long id, int amount  );

    /**
     * Subtracts an amount from the wallet of a user.
     *
     * @param id the ID of the user.
     * @param amount the amount to subtract.
     * @return a boolean indicating whether the operation was successful.
     */
    boolean subWallet(long id, int amount);
}
