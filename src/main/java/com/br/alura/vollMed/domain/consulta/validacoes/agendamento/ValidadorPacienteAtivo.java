package com.br.alura.vollMed.domain.consulta.validacoes.agendamento;

import com.br.alura.vollMed.domain.ValidacaoException;
import com.br.alura.vollMed.domain.consulta.DadosAgendamentoConsulta;
import com.br.alura.vollMed.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados){
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluido");
        }
    }
}
