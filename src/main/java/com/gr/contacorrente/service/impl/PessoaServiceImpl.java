package com.gr.contacorrente.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gr.contacorrente.exception.PessoaException;
import com.gr.contacorrente.model.Pessoa;
import com.gr.contacorrente.repository.PessoaRepository;
import com.gr.contacorrente.service.PessoaService;

/**
 * Service para Pessoa
 * @author gustavogrossmann
 *
 */
@Service
@Transactional
public class PessoaServiceImpl implements PessoaService{

	@Autowired private PessoaRepository pessoaRepository;
	
	@Override
	public List<Pessoa> getAll() {
		return pessoaRepository.findAll();
	}
	
	@Override
	public Pessoa findById(long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if(pessoa.isPresent()) {
			return pessoa.get();
		}
		throw new PessoaException("Pessoa não encontrada com o ID: " + id);
	}
}
