package com.example.teste.conhecimento.model.dto;

import com.example.teste.conhecimento.model.enums.IdadeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaImcIdadeDTO {

    private String faixaEtariaIdade;

    private Double mediaIMC;
}
