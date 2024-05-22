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
    Optional<UserDTO> update(UserDTO userDTO);
}
