package com.example.teste.conhecimento.service.impl;

import com.example.teste.conhecimento.model.dto.*;
import com.example.teste.conhecimento.model.enums.EstadoEnum;
import com.example.teste.conhecimento.model.enums.IdadeEnum;
import com.example.teste.conhecimento.model.enums.SexoEnum;
import com.example.teste.conhecimento.model.enums.TipoSanguineoEnum;
import com.example.teste.conhecimento.service.DadosService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class DadosServiceImpl implements DadosService {

    List<DadosJsonDTO> listaIMCCalculado;

    @Override
    public DadosRetornoDTO dadosProcessadosDoJson(List<DadosJsonDTO> dadosJsonDTOList) {
        calculaIMCCandidatos(dadosJsonDTOList); // carrega lista de imc

        //TODO: ATENÇÃO: Somente pessoas com idade de 16 a 69 anos e com peso acima de 50 Kg podem doar sangue. (está no scopo do teste)
        this.listaIMCCalculado = this.listaIMCCalculado.stream().filter(dados -> dados.getIdade() >= 16 && dados.getIdade() <= 69 && dados.getPeso() > 50).toList();

        DadosRetornoDTO dto = new DadosRetornoDTO();
        dto.setCandidatosPorEstadoDTOList(pegarCandidatosPorEstado());
        dto.setMediaImcIdadeDTOS(imcPorIdade());
        dto.setPercentualObesosDTOS(percentualObesos());
        dto.setDadosTipoSanguineoDTOS(dadosTipoSanguineo());
        return dto;
    }

    private List<CandidatosPorEstadoDTO> pegarCandidatosPorEstado() {
        List<CandidatosPorEstadoDTO> candidatosPorEstadoDTOS = new ArrayList<>();
        for (EstadoEnum estado : EstadoEnum.values()) {
            candidatosPorEstadoDTOS.add(candidatoPorEstado(estado));
        }

        return candidatosPorEstadoDTOS;
    }

    private void calculaIMCCandidatos(List<DadosJsonDTO> dadosJsonDTOList) {
        dadosJsonDTOList.forEach(dados -> {
            double imc = dados.getPeso() / (dados.getAltura() * dados.getAltura());
            dados.setImc(imc);
            dados.setIdade(idadePorDataNasc(dados.getData_nasc()));
        });
        this.listaIMCCalculado = dadosJsonDTOList;
    }

    private List<MediaImcIdadeDTO> imcPorIdade() {

        List<MediaImcIdadeDTO> mediaImcIdadeDTOS = new ArrayList<>();

        for (IdadeEnum idadeEnum : IdadeEnum.values()) {
            List<DadosJsonDTO> dadosJsonDTOS = this.listaIMCCalculado.stream().filter(dados -> dados.getIdade() >= idadeEnum.getMin()
                    && dados.getIdade() <= idadeEnum.getMax()).toList();
            double imcMax = dadosJsonDTOS.stream()
                    .mapToDouble(DadosJsonDTO::getImc)
                    .max()
                    .orElse(0);

            double mediaImc = imcMax / dadosJsonDTOS.size();
            if (Double.isNaN(mediaImc)) {
                mediaImc = 0.0;
            }
            mediaImcIdadeDTOS.add(MediaImcIdadeDTO.builder()
                    .mediaIMC(converterParaDouble(String.valueOf(mediaImc)))
                    .faixaEtariaIdade(idadeEnum.getFaixa()).build());
        }

        return mediaImcIdadeDTOS;
    }

    private List<PercentualObesosDTO> percentualObesos() {

        List<PercentualObesosDTO> percentualObesosDTO = new ArrayList<>();

        for (SexoEnum sexoEnum : SexoEnum.values()) {
            List<DadosJsonDTO> listaSexualidade = this.listaIMCCalculado.stream().filter(dados -> dados.getSexo().equals(sexoEnum.getSexo())).toList();
            List<DadosJsonDTO> listaObesidade = listaSexualidade.stream().filter(dados -> dados.getImc() > 30.0).toList();

            Double percentualObesos = ((double) listaObesidade.size() / listaSexualidade.size()) * 100;

            percentualObesosDTO.add(PercentualObesosDTO.builder().sexo(sexoEnum).percentualObesos(converterParaDouble(String.valueOf(percentualObesos))).build());
        }
        return percentualObesosDTO;
    }

    private List<DadosTipoSanguineoDTO> dadosTipoSanguineo() {
        List<DadosTipoSanguineoDTO> dadosTipoSanguineo = new ArrayList<>();

        for (TipoSanguineoEnum tipoSanguineoEnum : TipoSanguineoEnum.values()) {
            List<DadosJsonDTO> listaMediaIdadeTipo = this.listaIMCCalculado.stream().filter(dados -> dados.getTipo_sanguineo().equals(tipoSanguineoEnum.getTipo())).toList();
            int idadeMax = listaMediaIdadeTipo.stream()
                    .mapToInt(DadosJsonDTO::getIdade)
                    .sum();

            double mediaIdade = (double) idadeMax / listaMediaIdadeTipo.size();

            // criar metodo novo
            int qtdPessoas = qtdDoadoresPorTipoSangue(tipoSanguineoEnum);

            dadosTipoSanguineo.add(DadosTipoSanguineoDTO.builder().qtdPessoasDoadoras(qtdPessoas).tipoSanguineo(tipoSanguineoEnum.getTipo()).mediaPorIdade(converterParaDouble(String.valueOf(mediaIdade))).build());

        }
        return dadosTipoSanguineo;
    }

    private int qtdDoadoresPorTipoSangue(TipoSanguineoEnum tipoSanguineoEnum) {
        String[] elementosArray = tipoSanguineoEnum.getReceptor().split(",");
        List<String> elementosLista = Arrays.asList(elementosArray);
        AtomicInteger qtdSomadaPessoas = new AtomicInteger();
        elementosLista.forEach(tipoSanguineo -> {
            int qtdPessoas = this.listaIMCCalculado.stream().filter(dados -> dados.getTipo_sanguineo().equals(tipoSanguineo) && dados.getIdade() >= 16 && dados.getIdade() <= 69 && dados.getPeso() > 50).toList().size();
            qtdSomadaPessoas.addAndGet(qtdPessoas);
        });
        return qtdSomadaPessoas.get();
    }

    public CandidatosPorEstadoDTO candidatoPorEstado(EstadoEnum estadoEnum) {
        Integer qtdPessoas = this.listaIMCCalculado.stream().filter(dados -> dados.getEstado().equals(estadoEnum.getSigla())).collect(Collectors.toCollection(ArrayList::new)).size();
        return CandidatosPorEstadoDTO.builder().estado(estadoEnum).quantidade(qtdPessoas).build();
    }

    private int idadePorDataNasc(String dataNasc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataDeNascimento = LocalDate.parse(dataNasc, formatter);

        LocalDate dataAtual = LocalDate.now();

        Period periodo = Period.between(dataDeNascimento, dataAtual);

        return periodo.getYears();
    }

    private static double converterParaDouble(String valorStr) {
        try {
            // Crie um formato com o Locale apropriado
            DecimalFormat formato = new DecimalFormat("0.00");

            // Faça o parse do valor com o formato apropriado
            return formato.parse(formato.format(Double.valueOf(valorStr))).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}






