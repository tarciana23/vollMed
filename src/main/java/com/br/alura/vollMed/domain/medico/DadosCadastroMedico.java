package com.br.alura.vollMed.domain.medico;

import com.br.alura.vollMed.domain.Endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(

        @NotBlank//não é nulo nem vazio
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull//aqui é assim pq notblank é apenas para springs e aqui é um enum
        Especialidade especialidade,

        @NotNull @Valid//esse valid é pra apontar q é outro DTO, que é pra validar esse objeto
        DadosEndereco endereco) {
}

