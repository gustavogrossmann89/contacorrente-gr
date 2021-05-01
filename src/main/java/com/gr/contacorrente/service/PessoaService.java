package com.gr.contacorrente.service;

import java.util.List;

import com.gr.contacorrente.model.Pessoa;

public interface PessoaService {
	
	public List<Pessoa> getAll();
	public Pessoa findById(long id);
}
