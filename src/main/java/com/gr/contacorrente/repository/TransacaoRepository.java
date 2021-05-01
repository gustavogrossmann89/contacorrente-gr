package com.gr.contacorrente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.contacorrente.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
	
}
