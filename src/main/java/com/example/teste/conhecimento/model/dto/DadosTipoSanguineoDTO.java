package com.example.teste.conhecimento.model.dto;

import com.example.teste.conhecimento.model.enums.TipoSanguineoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosTipoSanguineoDTO {

    private double mediaPorIdade;

    private String tipoSanguineo;

    private int qtdPessoasDoadoras;
}
