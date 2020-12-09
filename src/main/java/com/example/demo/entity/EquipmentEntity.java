package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "equipment")
public class EquipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long equipmentId;

    @NotNull(message = "Null Field : nome" )
    @Column(name = "nome")
    private String nome;

    @NotNull(message = "Null Field : numSerie" )
    @Column(name = "numSerie")
    private String numSerie;
}