package com.br.alura.vollMed.Controller;

import com.br.alura.vollMed.domain.consulta.AgendaDeConsultas;
import com.br.alura.vollMed.domain.consulta.DadosAgendamentoConsulta;
import com.br.alura.vollMed.domain.consulta.DadosCancelamentoConsulta;
import com.br.alura.vollMed.domain.consulta.DadosDetalhamentoConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

/*    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados){
        var dto = agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }*/
}
