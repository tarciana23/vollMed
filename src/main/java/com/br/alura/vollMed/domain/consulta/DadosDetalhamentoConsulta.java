package com.br.alura.vollMed.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idPaciente, Long idMedico,LocalDateTime data) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(),consulta.getPaciente().getId(),consulta.getData());
    }
}
