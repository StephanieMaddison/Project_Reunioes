package com.example.demo.controller;

import com.example.demo.dto.EquipmentDTO;
import com.example.demo.exception.BusinessException;
import com.example.demo.model.Equipment;
import com.example.demo.repository.EquipmentRepository;
import com.example.demo.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("equipments")
public class EquipmentController extends IController {

    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    EquipmentService equipmentService;

    @GetMapping(URL_EQ)
    public ResponseEntity<?> getAllEquipment(){
        return  ResponseEntity.status(HttpStatus.OK).body(equipmentService.getAllEquipment());
    }

    @GetMapping(URL_ID_EQ)
    public ResponseEntity<?> getEquipmentById(@PathVariable(value = "id") Long equipmentId) throws BusinessException {
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.getEquipmentById(equipmentId));
    }

    @PostMapping(URL_EQ)
    public ResponseEntity<?> createEquipment(@RequestBody EquipmentDTO equipment) throws BusinessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentService.createEquipment(equipment.transformModel()));
    }

    @PutMapping(URL_ID_EQ)
    public ResponseEntity<?> updateEquipment(@PathVariable(value= "id") Long equipmentId, @Validated @RequestBody EquipmentDTO equipmentDTO) throws BusinessException {
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.updateEquipment(equipmentId, equipmentDTO.transformModel()));
    }

    @DeleteMapping(URL_ID_EQ)
    public ResponseEntity<?> deleteEquipment(@PathVariable(value = "id")Long equipmentId) throws BusinessException {
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.deleteEquipment(equipmentId));
    }


}
