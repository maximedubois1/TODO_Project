package com.sp.controller;

import com.sp.model.dto.RoomDTO;
import com.sp.service.RoomsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomsController {

    private final RoomsService roomsService;

    public RoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAll() {
        return ResponseEntity.ok(this.roomsService.getAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<RoomDTO> getRoomByName(HttpServletRequest request, @PathVariable String name) {
        RoomDTO room = this.roomsService.getRoomByName(name).orElse(null);
        if (room == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(room);

    }

    @PostMapping("/new")
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO room = this.roomsService.createRoom(roomDTO);
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteRoom(@PathVariable String name) {
        this.roomsService.deleteRoom(name);
        return ResponseEntity.ok("Room deleted");
    }

    @PostMapping("/join/{name}/{userID}")
    public ResponseEntity<String> joinRoom(@PathVariable String name) {
        return ResponseEntity.ok("Room joined");
    }

    @PostMapping("/addOwnerCard/{roomName}/{cardID}")
    public ResponseEntity<String> addOwnerCard(@PathVariable String roomName, @PathVariable Long cardID) {
        this.roomsService.addOwnerCard(roomName, cardID);
        return ResponseEntity.ok("Owner card added");
    }

    @PostMapping("/addOpponentCard/{roomName}/{cardID}")
    public ResponseEntity<String> addOpponentCard(@PathVariable String roomName, @PathVariable Long cardID) {
        this.roomsService.addOpponentCard(roomName, cardID);
        return ResponseEntity.ok("Opponent card added");
    }

}
