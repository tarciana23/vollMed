package com.br.alura.vollMed.domain.consulta.validacoes.agendamento;

import com.br.alura.vollMed.domain.ValidacaoException;
import com.br.alura.vollMed.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsultas {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        //Esse método getDayOfWeek retorna um booleano caso o dia seja domingo
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaClinica = dataConsulta.getHour() <7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        if(domingo || antesDaAberturaClinica || depoisDoEncerramentoDaClinica){
            throw new ValidacaoException("Consulta fora do horário da clínica");
        }
    }
}
