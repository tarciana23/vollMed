package com.br.alura.vollMed.paciente;

import com.br.alura.vollMed.Endereco.DadosEndereco;
import jakarta.validation.Valid;

public record DadosListagemPaciente(Long id, String nome, String email, String cpf) {
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}