package com.example.teste.conhecimento.controller;

import com.example.teste.conhecimento.model.dto.DadosJsonDTO;
import com.example.teste.conhecimento.service.DadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dados")
public class DadosController {

    @Autowired
    private DadosService dadosService;

    @PostMapping("/json")
    public ResponseEntity<?> dados(@RequestBody List<DadosJsonDTO> dadosJsonDTOS) {
        return ResponseEntity.ok(dadosService.dadosProcessadosDoJson(dadosJsonDTOS));
    }

}
