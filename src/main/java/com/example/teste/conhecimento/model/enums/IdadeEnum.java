package com.example.teste.conhecimento.model.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum IdadeEnum {
    ZERO_A_DEZ("0 à 10", 0, 10),
    ONZE_A_VINTE("11 à 20", 11, 20),
    VINTE_UM_A_TRINTA("21 à 30",21, 30),
    TRINTA_UM_A_QUARENTA("31 à 40",31, 40),
    QUARENTA_UM_A_CINQUENTA("41 à 50", 41, 50),
    CINQUENTA_UM_A_SESENTA("51 à 60", 51,60),
    SESENTA_UM_A_SETENTA("61 à 70", 61,70),
    SETENTA_UM_A_OITENTA("71 à 80", 71,80);

    private String faixa;
    private Integer min;
    private Integer max;
}
