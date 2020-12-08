package com.example.demo.mapper;

import com.example.demo.entity.ParticipantEntity;
import com.example.demo.model.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class ParticipantMapper {

    public static ParticipantEntity marshall(Participant model){
        return new ParticipantEntity(model.getId(),model.getName(),model.getEmail());
    }
    public static Participant unmarshall(ParticipantEntity entity){
        return new Participant(entity.getId(), entity.getName(), entity.getEmail());
    }
    public static List<ParticipantEntity> marshall(List<Participant> listParticipant){
        return listParticipant.stream().map(  participant -> marshall(participant)).collect(Collectors.toList());
    }
    public static List<Participant> unmarshall(List<ParticipantEntity> listEntity){
        return listEntity.stream().map( entity -> unmarshall(entity)).collect(Collectors.toList());
    }
}
