package com.example.demo.service;

import com.example.demo.exception.BusinessException;
import com.example.demo.entity.RoomEntity;
import com.example.demo.mapper.ParticipantMapper;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.model.Participant;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room createRoom(Room room) throws BusinessException {
        scheduleValidation(room);
//        RoomEntity entity = RoomMapper.marshall(room);
//        RoomEntity entitySalva = roomRepository.save(entity);
//        Room retorno = RoomMapper.unmarshall(entitySalva);
//        return retorno;
        return RoomMapper.unmarshall(roomRepository.save(RoomMapper.marshall(room)));
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
