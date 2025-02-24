package com.br.alura.vollMed.domain.medico;

import com.br.alura.vollMed.domain.Endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull//é obrigatório para identificar o médico
        Long id,
        String telefone,
        String email,
        String nome,
        DadosEndereco endereco) {
}
