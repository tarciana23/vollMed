package com.br.alura.vollMed.domain.paciente;

import com.br.alura.vollMed.domain.Endereco.DadosEndereco;
import jakarta.validation.Valid;

public record DadosAtualizacaoPaciente(
        Long id,
        String nome,
        String telefone,
        @Valid DadosEndereco endereco) {

}
