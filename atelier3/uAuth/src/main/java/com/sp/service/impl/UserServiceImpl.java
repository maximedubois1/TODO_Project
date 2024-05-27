package com.sp.service.impl;

import com.sp.mapper.UserMapper;
import com.sp.model.UserEntity;
import com.sp.model.dto.UserDTO;
import com.sp.repository.UserRepository;
import com.sp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAll() {
        return this.userMapper.toDTOs(this.userRepository.findAll());
    }

    @Override
    public Optional<UserDTO> getById(Long id) {
        return this.userRepository.findById(id)
                .map(this.userMapper::toDTO);
    }

    @Override
    public Optional<UserDTO> create(UserDTO userDTO) {
        return Optional.of(this.userMapper.toDTO(
                this.userRepository.save(this.userMapper.toEntity(userDTO))
        ));
    }

    @Override
    public Optional<UserDTO> update(UserDTO userDTO) {
        return Optional.of(this.userMapper.toDTO(
                this.userRepository.save(this.userMapper.toEntity(userDTO))
        ));
    }

    @Override
    public boolean testWallet(long id, int amount) {
        UserEntity user = this.userRepository.findById(id).orElse(null);
        if (user != null)
            return user.getWallet() >= amount;
        throw new IllegalArgumentException("User not found");
    }

    @Override
    public boolean addWallet(long id, int amount) {
        UserEntity user = this.userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setWallet(user.getWallet() + amount);
            this.userRepository.save(user);
            return true;
        }
        throw new IllegalArgumentException("User not found");
    }

    @Override
    public boolean subWallet(long id, int amount) {
        UserEntity user = this.userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setWallet(user.getWallet() - amount);
            if (user.getWallet() < 0)
                throw new IllegalArgumentException("Not enough money");
            this.userRepository.save(user);
            return true;
        }
        throw new IllegalArgumentException("User not found");
    }

    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public UserDTO getBySurname(String surname) {
        return this.userMapper.toDTO(this.userRepository.findBySurname(surname));
    }
}
