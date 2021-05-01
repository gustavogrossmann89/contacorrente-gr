package com.gr.contacorrente.service;

import java.util.List;

import com.gr.contacorrente.model.PessoaJuridica;

public interface PessoaJuridicaService {

	public List<PessoaJuridica> getAll();
	public PessoaJuridica findById(long id);
	public PessoaJuridica cadastrarCliente(PessoaJuridica pessoaJuridica);
	public PessoaJuridica atualizarCliente(PessoaJuridica pessoaJuridica);
}
