package com.gr.contacorrente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr.contacorrente.model.PessoaJuridica;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long>{

	public PessoaJuridica findByCnpj(String cnpj);
}
