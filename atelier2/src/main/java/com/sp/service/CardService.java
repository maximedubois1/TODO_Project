package com.sp.service;

import com.sp.model.dto.CardDTO;

import java.util.List;
import java.util.Optional;

public interface CardService {
    List<CardDTO> getAll();
    Optional<List<CardDTO>> getByUserId(Long userId); // those which belongs to a user
    List<CardDTO> getAvailableCards(); // those on marketplace
    Optional<CardDTO> getById(Long id);
    void generateFiveCardsForUser(Long userId);
    void setOwner(Long userid, CardDTO carddto);
}
