package com.br.alura.vollMed.domain.consulta.validacoes.agendamento;

import com.br.alura.vollMed.domain.ValidacaoException;
import com.br.alura.vollMed.domain.consulta.DadosAgendamentoConsulta;
import com.br.alura.vollMed.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados){
        //escola do medico opcional
        if(dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído!");
        }
    }
}
