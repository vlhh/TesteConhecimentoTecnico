package com.example.teste.conhecimento.model.dto;

import com.example.teste.conhecimento.model.enums.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidatosPorEstadoDTO {
    private Integer quantidade;
    private EstadoEnum estado;
}
