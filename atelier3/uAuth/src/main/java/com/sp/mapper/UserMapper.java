package com.sp.mapper;

import com.sp.model.UserEntity;
import com.sp.model.dto.UserDTO;
import com.sp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserMapper {

    private final UserRepository userRepository;

    public UserMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO toDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setWallet(user.getWallet());
        return userDTO;
    }

    public UserEntity toEntity(UserDTO userDTO) {
        Optional<UserEntity> userEnt = this.userRepository.findById(userDTO.getId());

        UserEntity user = new UserEntity();
        if (userDTO.getId() != null)
            user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setWallet(userDTO.getWallet());
        userEnt.ifPresent(userEntity -> user.setPassword(userEntity.getPassword()));

        return user;
    }

    public List<UserDTO> toDTOs(List<UserEntity> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity user : users) {
            userDTOs.add(toDTO(user));
        }
        return userDTOs;
    }

    public List<UserEntity> toEntities(List<UserDTO> userDTOs) {
        List<UserEntity> users = new ArrayList<>();
        for (UserDTO userDTO : userDTOs) {
            users.add(toEntity(userDTO));
        }
        return users;
    }
}
