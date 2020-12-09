package com.example.demo.controller;

import com.example.demo.exception.BusinessException;
import com.example.demo.model.Equipment;
import com.example.demo.repository.EquipmentRepository;
import com.example.demo.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<Equipment> getAllEquipment(){
        return equipmentService.getAllEquipment();
    }

    @GetMapping(URL_ID_EQ)
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable(value = "id") long equipmentId) throws BusinessException {
        return ResponseEntity.ok().body(equipmentService.getEquipmentById(equipmentId));
    }

    @PostMapping(URL_EQ)
    public Equipment createEquipment(@RequestBody Equipment equipment) throws BusinessException {
        return equipmentService.createEquipment(equipment);
    }

    @PutMapping(URL_ID_EQ)
    public ResponseEntity<Equipment> updateEquipment(@PathVariable(value= "id") long equipmentId, @Valid @RequestBody Equipment equipment) throws BusinessException {
        return ResponseEntity.ok(equipmentService.updateEquipment(equipmentId, equipment));
    }

    @DeleteMapping(URL_ID_EQ)
    public Map<String, Boolean> deleteEquipment(@PathVariable(value = "id")Long equipmentId) throws BusinessException {
        return equipmentService.deleteEquipment(equipmentId);
    }


}
