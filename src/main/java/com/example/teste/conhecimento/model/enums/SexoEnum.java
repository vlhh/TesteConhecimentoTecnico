package com.example.teste.conhecimento.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SexoEnum {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private String sexo;
}
