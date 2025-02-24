package com.br.alura.vollMed.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//passa como genérico a classe médico e o tipo do identificador
public interface MedicoRepository extends JpaRepository<Medico,Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}



