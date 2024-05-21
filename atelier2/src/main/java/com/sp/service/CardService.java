package com.sp.service;

import com.sp.model.dto.CardDTO;

import java.util.List;

public interface CardService {
    List<CardDTO> getAll();
    List<CardDTO> getByUserId(Long userId); // those which belongs to a user
    List<CardDTO> getAvailableCards(); // those on marketplace
    CardDTO getById(Long id);
}
