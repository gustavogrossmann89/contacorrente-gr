package com.gr.contacorrente.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gr.contacorrente.exception.PessoaException;
import com.gr.contacorrente.model.PessoaFisica;
import com.gr.contacorrente.repository.PessoaFisicaRepository;
import com.gr.contacorrente.service.PessoaFisicaService;
import com.gr.contacorrente.util.Util;

/**
 * Service para Pessoa Física
 * @author gustavogrossmann
 *
 */
@Service
@Transactional
public class PessoaFisicaServiceImpl implements PessoaFisicaService{

	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	
	@Override
	public List<PessoaFisica> getAll() {
		return pessoaFisicaRepository.findAll();
	}
	
	@Override
	public PessoaFisica findById(long id) {
		Optional<PessoaFisica> pessoa = pessoaFisicaRepository.findById(id);
		if(pessoa.isPresent()) {
			return pessoa.get();
		}
		throw new PessoaException("PessoaFisica não encontrada com o ID: " + id);
	}
	
	/**
	 * Cadastro de Cliente do tipo PessoaFisica
	 * Contém validação de CPF (deve conter 11 caracteres e ser um válido de acordo com as regras do país)
	 */
	@Override
	public PessoaFisica cadastrarCliente(PessoaFisica pessoaFisica) {
		
		if(pessoaFisica != null && StringUtils.isNotBlank(pessoaFisica.getCpf())) {
			pessoaFisica.setCpf(Util.removeNaoNumericos(pessoaFisica.getCpf()));
			
			PessoaFisica pf = pessoaFisicaRepository.findByCpf(pessoaFisica.getCpf());
			if(pf == null) {
				if(Util.validaCPF(pessoaFisica.getCpf())) {
					try {
						return pessoaFisicaRepository.save(pessoaFisica);
					} catch (Exception e) {
						throw new PessoaException("Não foi possível cadastrar o cliente: " + e.getMessage());		
					}
				} else {
					throw new PessoaException("O CPF informado para a pessoa é invalido");
				}
			} else {
				throw new PessoaException("Já existe um cliente com o CPF informado");
			}
		} else {
			throw new PessoaException("Não foi possível cadastrar o cliente: dados vazio ou nulos");
		}
	}

	/**
	 * Atualização de Cliente do tipo PessoaFisica
	 * Contém validação de CPF (deve conter 11 caracteres e ser um válido de acordo com as regras do país)
	 */
	@Override
	public PessoaFisica atualizarCliente(PessoaFisica pessoaFisica) {
		Optional<PessoaFisica> pessoaDb = pessoaFisicaRepository.findById(pessoaFisica.getId());
		if(pessoaDb.isPresent()) {
			PessoaFisica pessoaUpdate = pessoaDb.get();
			
			if(pessoaFisica != null && StringUtils.isNotBlank(pessoaFisica.getCpf())) {
				if(Util.validaCPF(pessoaFisica.getCpf())) {
					pessoaUpdate.setId(pessoaFisica.getId());
					pessoaUpdate.setNome(pessoaFisica.getNome());
					pessoaUpdate.setCpf(Util.removeNaoNumericos(pessoaFisica.getCpf()));
					
					try {
						return pessoaFisicaRepository.save(pessoaUpdate);
					} catch (Exception e) {
						throw new PessoaException("Não foi possível atualizar os dados do cliente: " + e.getMessage());		
					}
				} else {
					throw new PessoaException("O CPF informado para a pessoa é invalido");
				}
			} else {
				throw new PessoaException("Não foi possível atualizar os dados do cliente: dados vazio ou nulos");
			}
		} else {
			throw new PessoaException("PessoaFisica não encontrada com o ID: " + pessoaFisica.getId());
		}
	}
}
