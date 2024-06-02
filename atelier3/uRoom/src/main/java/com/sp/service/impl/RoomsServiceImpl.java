package com.sp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp.mapper.RoomMapper;
import com.sp.model.RoomEntity;
import com.sp.model.dto.FightDTO;
import com.sp.model.dto.RoomDTO;
import com.sp.repository.RoomRepository;
import com.sp.service.RoomsService;
import com.sp.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RoomsServiceImpl implements RoomsService {

    RoomRepository roomRepository;
    RoomMapper roomMapper;

    @Value("${ucards.url}")
    private String ucardurl;

    public RoomsServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public List<RoomDTO> getAll() {
        List<RoomEntity> roomEntities = this.roomRepository.AvailableRooms();
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
        } else {
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

    @Override
    public void setWinner(String roomName, Long userID) {
        RoomEntity roomEntity = this.roomRepository.findByName(roomName).get();
        roomEntity.setWinnerID(userID);
    }

    @Override
    public Long fight(FightDTO fightDTO) throws IOException {
        RoomEntity roomEntity = this.roomRepository.findByName(fightDTO.getRoomName()).get();
        if (roomEntity.getWinnerID() != null) {
            return roomEntity.getWinnerID();
        }
        fightDTO.setBet(roomEntity.getBet());
        fightDTO.setOpponentID(roomEntity.getOpponentID());
        fightDTO.setOwnerID(roomEntity.getOwnerID());
        fightDTO.setOwnerCardID(roomEntity.getOwnerCardID());
        fightDTO.setOpponentCardID(roomEntity.getOpponentCardID());
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("fightDTO: " + objectMapper.writeValueAsString(fightDTO));
        String response = HttpUtils.sendPostRequest(ucardurl + "/api/v1/cards/fight", objectMapper.writeValueAsString(fightDTO));
        Long winnerID = objectMapper.readValue(response, Long.class);
        roomEntity.setWinnerID(winnerID);
        this.roomRepository.save(roomEntity);
        return winnerID;
    }
}
