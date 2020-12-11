package com.example.demo.service;

import com.example.demo.dto.EquipmentDTOResponse;
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
import java.util.stream.Collectors;

@Service
public class EquipmentService {

    @Autowired
    EquipmentRepository equipmentRepository;

    public EquipmentDTOResponse createEquipment(Equipment equipment) throws BusinessException {
        scheduleValidation(equipment);

        Equipment repositoryResponse = EquipmentMapper.unmarshall(equipmentRepository.save(EquipmentMapper.marshall(equipment)));
        return new EquipmentDTOResponse(repositoryResponse.getId(), repositoryResponse.getNome(), repositoryResponse.getNumSerie());
    }

    private void scheduleValidation(Equipment equipmentEntity) throws BusinessException {
        if(equipmentEntity.getNome().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_FIELD);

        if(equipmentEntity.getNumSerie().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_FIELD);
    }

    public  List<EquipmentDTOResponse> getAllEquipment(){
        List<EquipmentDTOResponse> equipmentDTOResponseList = EquipmentMapper.unmarshall(equipmentRepository.findAll())
                .stream().map( equipment -> new EquipmentDTOResponse(equipment.getId(), equipment.getNome(), equipment.getNumSerie())).collect(Collectors.toList());
        return equipmentDTOResponseList;
    }
    public EquipmentDTOResponse getEquipmentById(Long equipmentId) throws BusinessException {
        Equipment repositoryResponse = EquipmentMapper.unmarshall(equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new BusinessException(BusinessException.EQUIPMENT_NOT_FOUND+": "+equipmentId)));
        return new EquipmentDTOResponse(repositoryResponse.getId(), repositoryResponse.getNome(), repositoryResponse.getNumSerie());
    }

    public EquipmentDTOResponse updateEquipment(Long equipmentId, Equipment equipment) throws BusinessException {
        scheduleValidation(equipment);
        EquipmentEntity entity = equipmentRepository.findById(equipmentId).orElseThrow(() -> new BusinessException(BusinessException.EQUIPMENT_NOT_FOUND + ": " + equipmentId));
        equipment.setId(equipmentId);
        Equipment repositoryResponse = EquipmentMapper.unmarshall(equipmentRepository.save(EquipmentMapper.marshall(equipment)));
        return new EquipmentDTOResponse(repositoryResponse.getId(), repositoryResponse.getNome(), repositoryResponse.getNumSerie());
    }

    public Map<String, Boolean> deleteEquipment(Long equipmentId) throws BusinessException {
        Optional<EquipmentEntity> equipment =  equipmentRepository.findById(equipmentId);

        if(equipment.isEmpty())
            throw new BusinessException(BusinessException.EQUIPMENT_NOT_FOUND + ": " + equipmentId);

        equipmentRepository.delete(equipment.get());
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);

        return response;
    }
}