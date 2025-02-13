package com.br.alura.vollMed.medico;

import com.br.alura.vollMed.Endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull//é obrigatório para identificar o médico
        Long id,
        String telefone,
        String email,
        String nome,
        DadosEndereco endereco) {
}
