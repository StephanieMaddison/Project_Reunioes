package com.example.demo.mapper;

import com.example.demo.entity.ParticipantEntity;
import com.example.demo.entity.RoomEntity;
import com.example.demo.model.Participant;
import com.example.demo.model.Room;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class RoomMapper {
    
    public static RoomEntity marshall(Room model){
        return new RoomEntity(model.getId(), model.getName(),model.getDate(),model.getStartHour(),model.getEndHour(),model.getParticipants()
                .stream().map( Participant  ->  new ParticipantEntity(Participant.getId(), Participant.getName(), Participant.getEmail())).collect(Collectors.toList()));
    }
    public static Room unmarshall(RoomEntity entity){
        return new Room(entity.getId(), entity.getName(),entity.getDate(),entity.getStartHour(),entity.getEndHour(),entity.getParticipants()
                .stream().map( ParticipantEntity  ->  new Participant(ParticipantEntity.getId(), ParticipantEntity.getName(), ParticipantEntity.getEmail())).collect(Collectors.toList()));
    }
    public static  List<RoomEntity> marshall (List<Room> listRoom){
        return listRoom.stream().map(  room -> marshall(room)  ).collect(Collectors.toList());
    }
    public static List<Room> unmarshall (List<RoomEntity> listEntity){
        return  listEntity.stream().map( entity -> unmarshall(entity) ).collect(Collectors.toList());
    }

}
