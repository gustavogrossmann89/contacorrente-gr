package com.gr.contacorrente.service;

import java.util.List;

import com.gr.contacorrente.model.PessoaFisica;

public interface PessoaFisicaService {

	public List<PessoaFisica> getAll();
	public PessoaFisica findById(long id);
	public PessoaFisica cadastrarCliente(PessoaFisica pessoaFisica);
	public PessoaFisica atualizarCliente(PessoaFisica pessoaFisica);
}
