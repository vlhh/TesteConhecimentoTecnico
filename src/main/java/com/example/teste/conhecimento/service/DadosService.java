package com.example.teste.conhecimento.service;

import com.example.teste.conhecimento.model.dto.DadosJsonDTO;
import com.example.teste.conhecimento.model.dto.DadosRetornoDTO;

import java.util.List;

public interface DadosService {
    DadosRetornoDTO dadosProcessadosDoJson(List<DadosJsonDTO> dadosJsonDTOList);
}
