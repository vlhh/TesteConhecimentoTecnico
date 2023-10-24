package com.example.teste.conhecimento.model.dto;

import com.example.teste.conhecimento.model.enums.SexoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PercentualObesosDTO {

    private SexoEnum sexo;

    private Double percentualObesos;
}
