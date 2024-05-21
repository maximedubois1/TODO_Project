package com.sp.service;

import com.sp.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAll();
    Optional<UserDTO> getById(Long id);
    Optional<UserDTO> create(UserDTO userDTO);
}
