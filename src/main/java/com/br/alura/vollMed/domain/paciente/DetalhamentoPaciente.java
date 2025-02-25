package com.br.alura.vollMed.domain.paciente;

import com.br.alura.vollMed.domain.Endereco.Endereco;

public record DetalhamentoPaciente(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {

    public DetalhamentoPaciente(Paciente paciente){
        this(paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getCpf(),
                paciente.getEndereco());
    }
}
