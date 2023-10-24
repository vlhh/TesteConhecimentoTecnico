package com.example.teste.conhecimento.model.dto;

import com.example.teste.conhecimento.model.enums.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosRetornoDTO {
    private List<CandidatosPorEstadoDTO> candidatosPorEstadoDTOList;
    private List<MediaImcIdadeDTO> mediaImcIdadeDTOS;
    private List<PercentualObesosDTO> percentualObesosDTOS;

    private List<DadosTipoSanguineoDTO> dadosTipoSanguineoDTOS;
}
