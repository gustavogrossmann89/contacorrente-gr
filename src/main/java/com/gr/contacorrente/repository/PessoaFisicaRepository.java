package com.gr.contacorrente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.contacorrente.model.PessoaFisica;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long>{

	public PessoaFisica findByCpf(String cpf);
}
