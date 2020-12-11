package com.example.demo.dto;

import com.example.demo.model.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDTO {

    private Long id;
    private String nome;
    private String numSerie;

    public Equipment transformModel(){
        return new Equipment(id,nome,numSerie);
    }
}
