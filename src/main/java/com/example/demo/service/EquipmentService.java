package com.example.demo.service;

import com.example.demo.entity.EquipmentEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.EquipmentMapper;
import com.example.demo.model.Equipment;
import com.example.demo.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    EquipmentRepository equipmentRepository;

    public Equipment createEquipment(Equipment equipment) throws BusinessException {
        scheduleValidation(equipment);
        return EquipmentMapper.unmarshall(equipmentRepository.save(EquipmentMapper.marshall(equipment)));
    }

    private void scheduleValidation(Equipment equipmentEntity) throws BusinessException {
        if(equipmentEntity.getNome().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_FIELD);

        if(equipmentEntity.getNumSerie().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_FIELD);
    }

    public List<Equipment> getAllEquipment(){
        return EquipmentMapper.unmarshall(equipmentRepository.findAll());
    }

    public Equipment getEquipmentById(long equipmentId) throws BusinessException {

        return  EquipmentMapper.unmarshall(equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new BusinessException(BusinessException.ROOM_NOT_FOUND+": "+equipmentId)));
    }

    public Equipment updateEquipment(long equipmentId, Equipment equipment) throws BusinessException {

        EquipmentEntity entity = equipmentRepository.findById(equipmentId).orElseThrow(() -> new BusinessException(BusinessException.ROOM_NOT_FOUND + ": " + equipmentId));
        scheduleValidation(equipment);
        equipment.setId(equipmentId);
        return  EquipmentMapper.unmarshall(equipmentRepository.save(EquipmentMapper.marshall(equipment)));
    }

    public Map<String, Boolean> deleteEquipment(long equipmentId) throws BusinessException {
        Optional<EquipmentEntity> equipment =  equipmentRepository.findById(equipmentId);

        if(equipment.isEmpty())
            throw new BusinessException(BusinessException.ROOM_NOT_FOUND + ": " + equipmentId);

        equipmentRepository.delete(equipment.get());
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);

        return response;
    }
}