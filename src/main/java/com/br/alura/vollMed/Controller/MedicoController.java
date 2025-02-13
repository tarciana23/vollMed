package com.br.alura.vollMed.Controller;


import com.br.alura.vollMed.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional //indica uma transição ativa com o banco de dados
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){

        repository.save(new Medico(dados));
    }

    //Esse parâmetro pageable é opcional, caso não passado é impresso os valores por padrao: todos os valores do bd sem ordenacao
    //Podemos alterar esse padrão com PageableDefault(size=10,sort={crm}
    @GetMapping
    public Page<DadosListagemMedico> listar(Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional //todo esse trecho de código vai rodar dentro de uma transação
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados ){
        var medico = repository.getReferenceById(dados.id()); //carregou o médico pelo id
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }

}
