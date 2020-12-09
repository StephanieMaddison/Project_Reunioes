package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {

    private Long id;

    @NotNull(message = "Null Field : nome" )
    private String nome;

    @NotNull(message = "Null Field : numSerie" )
    private String numSerie;
}
