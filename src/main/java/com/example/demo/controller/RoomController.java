package com.example.demo.controller;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.Valid;
import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping ("api/v1")

public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") long roomId)
        throws ResourceNotFoundException{
            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new ResourceNotFoundException("Room Not Found: " +roomId));
            return ResponseEntity.ok().body(room);
        }

    @PostMapping("/rooms")
    public Room createRoom(@RequestBody Room room){
        return roomRepository.save(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value= "id") long roomId,
                                                    @Valid @RequestBody Room roomDetails) throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResolutionException("Room Not Found for this Id::" + roomId));

        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setStartHour(roomDetails.getStartHour());
        room.setEndHour(roomDetails.getStartHour());

        final Room updateRoom = roomRepository.save(room);
        return ResponseEntity.ok(updateRoom);

    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id")Long roomId)
        throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room Not Found for this Id:" + roomId));

        roomRepository.delete(room);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);

        return response;


    }



}