package com.gr.contacorrente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.contacorrente.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
}
