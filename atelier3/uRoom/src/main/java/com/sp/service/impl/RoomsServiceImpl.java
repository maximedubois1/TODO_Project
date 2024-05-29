package com.sp.service.impl;

import com.sp.mapper.RoomMapper;
import com.sp.model.RoomEntity;
import com.sp.model.dto.RoomDTO;
import com.sp.repository.RoomRepository;
import com.sp.service.RoomsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RoomsServiceImpl implements RoomsService {

    RoomRepository roomRepository;
    RoomMapper roomMapper;

    public RoomsServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public List<RoomDTO> getAll() {
        List<RoomEntity> roomEntities = this.roomRepository.findAll();
        return this.roomMapper.toDTOs(roomEntities);
    }

    @Override
    public Optional<RoomDTO> getRoomByName(String name) {
        Optional<RoomEntity> roomEntity = this.roomRepository.findByName(name);
        RoomDTO roomDTO = this.roomMapper.toDTO(roomEntity.get());
        return Optional.of(roomDTO);
    }

    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {
        RoomEntity roomEntity = new RoomEntity();
        System.out.println(roomDTO.getName());
        roomEntity.setName(roomDTO.getName());
        roomEntity.setBet(roomDTO.getBet());
        roomEntity.setOwnerID(roomDTO.getOwnerID());
        RoomEntity savedRoom = this.roomRepository.save(roomEntity);
        return this.roomMapper.toDTO(savedRoom);
    }

    @Override
    public void deleteRoom(String name) {
        this.roomRepository.deleteById(name);
    }

    @Override
    public void joinRoom(String roomName, Long userID) {
        RoomEntity roomEntity = this.roomRepository.findByName(roomName).get();
        if (Objects.equals(roomEntity.getOwnerID(), userID)) {
            roomEntity.setOwnerID(userID);
        }else {
            roomEntity.setOpponentID(userID);
        }
        this.roomRepository.save(roomEntity);
    }

    @Override
    public void setCard(String roomName, Long userID, Long cardID) {
        RoomEntity roomEntity = this.roomRepository.findByName(roomName).get();
        if (Objects.equals(roomEntity.getOwnerID(), userID)) {
            roomEntity.setOwnerCardID(cardID);
        } else {
            roomEntity.setOpponentCardID(cardID);
        }
        this.roomRepository.save(roomEntity);
    }

    @Override
    public void addOwnerCard(String roomName, Long cardID) {
        RoomEntity roomEntity = this.roomRepository.findByName(roomName).get();
        roomEntity.setOwnerCardID(cardID);
        this.roomRepository.save(roomEntity);
    }

    @Override
    public void addOpponentCard(String roomName, Long cardID) {
        RoomEntity roomEntity = this.roomRepository.findByName(roomName).get();
        roomEntity.setOpponentCardID(cardID);
        this.roomRepository.save(roomEntity);
    }
}
