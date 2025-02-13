package com.br.alura.vollMed.paciente;

import com.br.alura.vollMed.Endereco.DadosEndereco;
import jakarta.validation.Valid;

public record DadosAtualizacaoPaciente(
        Long id,
        String nome,
        String telefone,
        @Valid DadosEndereco endereco) {

}
