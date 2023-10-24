package com.example.teste.conhecimento.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TipoSanguineoEnum {

    A_MAIS("A+", "AB+,A+", "A+,A-,O+,O-"),
    A_MENOS("A-","A+,A-,AB+,AB-","A-,O-"),
    B_MAIS( "B+", "B+,AB+","B+,B-,O+,O-"),
    B_MENOS("B-","B+,B-,AB+,AB-","B-,O-"),
    AB_MAIS("AB+","AB+","A+,B+,O+,AB+,A-,B-,O-,AB-"),
    AB_MENOS("AB-","AB+,AB-","A-,B-,O-,AB-"),
    O_MAIS("O+","A+,B+,O+,AB+","O+,O-"),
    O_MENOS("O-","A+,B+,O+,AB+,A-,B-,O-,AB-","O-");




    private String tipo;
    private String doador;
    private String receptor;
}
