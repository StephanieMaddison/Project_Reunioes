package com.example.demo.service;

import com.example.demo.entity.EquipmentEntity;
import com.example.demo.entity.ParticipantEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.entity.RoomEntity;
import com.example.demo.mapper.ParticipantMapper;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.model.Equipment;
import com.example.demo.model.Participant;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room createRoom(Room room){
        ParticipantEntity participant = new ParticipantEntity(1L,"teste","teste");
        EquipmentEntity equipment = new EquipmentEntity(1l, "Slide", "001");
        List<ParticipantEntity> listEntity = new ArrayList<>();
        listEntity.add(participant);
        return RoomMapper.unmarshall(roomRepository.save( new RoomEntity(null,"teste", LocalDate.now(), LocalTime.of(12,00,00),
                LocalTime.of(13,00,00),  listEntity, equipment)));


//RoomMapper.unmarshall(roomRepository.save(RoomMapper.marshall(room)));

    }

    private void scheduleValidation(Room roomEntity) throws BusinessException {
        if(roomEntity.getDate().isBefore(LocalDate.now()))
            throw new BusinessException(BusinessException.INVALID_DATE);

        if(roomEntity.getStartHour().isAfter(roomEntity.getEndHour()))
            throw new BusinessException(BusinessException.TIME_ERROR);

        if(roomEntity.getStartHour().equals(roomEntity.getEndHour()))
            throw new BusinessException(BusinessException.TIME_ERROR);
    }

    public List<Room> getAllRooms(){
        return RoomMapper.unmarshall(roomRepository.findAll());
    }

    public Room getRoomById(Long roomId) throws BusinessException {

        return  RoomMapper.unmarshall(roomRepository.findById(roomId)
                .orElseThrow(() -> new BusinessException(BusinessException.ROOM_NOT_FOUND+": "+roomId)));
    }

    public Room updateRoom(Long roomId, Room room) throws BusinessException {

        RoomEntity entity = roomRepository.findById(roomId).orElseThrow(() -> new BusinessException(BusinessException.ROOM_NOT_FOUND + ": " + roomId));
        scheduleValidation(room);
        room.setId(roomId);
        return  RoomMapper.unmarshall(roomRepository.save(RoomMapper.marshall(room)));
    }

    public Map<String, Boolean> deleteRoom(Long roomId) throws BusinessException {
        Optional<RoomEntity> room =  roomRepository.findById(roomId);

        if(room.isEmpty())
            throw new BusinessException(BusinessException.ROOM_NOT_FOUND + ": " + roomId);

        roomRepository.delete(room.get());
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);

        return response;
    }
}
