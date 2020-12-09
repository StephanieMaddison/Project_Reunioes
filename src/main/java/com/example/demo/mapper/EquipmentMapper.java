package com.example.demo.mapper;

import com.example.demo.entity.EquipmentEntity;
import com.example.demo.entity.RoomEntity;
import com.example.demo.model.Equipment;
import com.example.demo.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class EquipmentMapper {

    public static EquipmentEntity marshall(Equipment model){
        return new EquipmentEntity(model.getId(), model.getNome(),model.getNumSerie());
    }

    public static Equipment unmarshall(EquipmentEntity entity){
        return new Equipment(entity.getEquipmentId(), entity.getNome(),entity.getNumSerie());
    }

    public static List<EquipmentEntity> marshall (List<Equipment> listEquipment){
        return listEquipment.stream().map(  equipment -> marshall(equipment)  ).collect(Collectors.toList());
    }

    public static List<Equipment> unmarshall (List<EquipmentEntity> listEntity){
        return  listEntity.stream().map( entity -> unmarshall(entity) ).collect(Collectors.toList());
    }
}