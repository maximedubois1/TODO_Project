package com.sp.mapper;

import com.sp.model.RoomEntity;
import com.sp.model.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class RoomMapper {

    public RoomDTO toDTO(RoomEntity roomEntity){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName(roomEntity.getName());
        roomDTO.setBet(roomEntity.getBet());
        roomDTO.setOwnerID(roomEntity.getOwnerID());
        roomDTO.setOwnerCardID(roomEntity.getOwnerCardID());
        roomDTO.setOpponentID(roomEntity.getOpponentID());
        roomDTO.setOpponentCardID(roomEntity.getOpponentCardID());
        return roomDTO;
    }

    public RoomEntity toEntity(RoomDTO roomDTO){
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setName(roomDTO.getName());
        roomEntity.setBet(roomDTO.getBet());
        roomEntity.setOwnerID(roomDTO.getOwnerID());
        roomEntity.setOwnerCardID(roomDTO.getOwnerCardID());
        roomEntity.setOpponentID(roomDTO.getOpponentID());
        roomEntity.setOpponentCardID(roomDTO.getOpponentCardID());
        return roomEntity;
    }

    public List<RoomDTO> toDTOs(List<RoomEntity> roomEntities){
        List<RoomDTO> roomDTOs = new ArrayList<>();
        for(RoomEntity roomEntity : roomEntities){
            roomDTOs.add(toDTO(roomEntity));
        }
        return roomDTOs;
    }

    public List<RoomEntity> toEntities(List<RoomDTO> roomDTOs){
        List<RoomEntity> roomEntities = new ArrayList<>();
        for(RoomDTO roomDTO : roomDTOs){
            roomEntities.add(toEntity(roomDTO));
        }
        return roomEntities;
    }
}
