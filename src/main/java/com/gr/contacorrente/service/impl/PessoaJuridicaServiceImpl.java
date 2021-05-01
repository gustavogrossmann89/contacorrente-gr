package com.gr.contacorrente.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gr.contacorrente.exception.PessoaException;
import com.gr.contacorrente.model.PessoaJuridica;
import com.gr.contacorrente.repository.PessoaJuridicaRepository;
import com.gr.contacorrente.service.PessoaJuridicaService;
import com.gr.contacorrente.util.Util;

/**
 * Service para Pessoa Jurídica
 * @author gustavogrossmann
 *
 */
@Service
@Transactional
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService{

	@Autowired private PessoaJuridicaRepository pessoaJuridicaRepository;
	
	@Override
	public List<PessoaJuridica> getAll() {
		return pessoaJuridicaRepository.findAll();
	}
	
	@Override
	public PessoaJuridica findById(long id) {
		Optional<PessoaJuridica> pessoa = pessoaJuridicaRepository.findById(id);
		if(pessoa.isPresent()) {
			return pessoa.get();
		}
		throw new PessoaException("PessoaJuridica não encontrada com o ID: " + id);
	}
	
	/**
	 * Cadastro de Cliente do tipo PessoaJuridica
	 * Contém validação de CNPJ (deve conter 14 caracteres e ser um válido de acordo com as regras do país)
	 */
	@Override
	public PessoaJuridica cadastrarCliente(PessoaJuridica pessoaJuridica) {
		
		if(pessoaJuridica != null && StringUtils.isNotBlank(pessoaJuridica.getCnpj())) {
			pessoaJuridica.setCnpj(Util.removeNaoNumericos(pessoaJuridica.getCnpj()));
			
			PessoaJuridica pj = pessoaJuridicaRepository.findByCnpj(pessoaJuridica.getCnpj());
			if(pj == null) {
				if(Util.validaCNPJ(pessoaJuridica.getCnpj())) {
					try {
						return pessoaJuridicaRepository.save(pessoaJuridica);
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
	 * Atualização de Cliente do tipo PessoaJuridica
	 * Contém validação de CNPJ (deve conter 14 caracteres e ser um válido de acordo com as regras do país)
	 */
	@Override
	public PessoaJuridica atualizarCliente(PessoaJuridica pessoaJuridica) {
		Optional<PessoaJuridica> pessoaDb = pessoaJuridicaRepository.findById(pessoaJuridica.getId());
		if(pessoaDb.isPresent()) {
			PessoaJuridica pessoaUpdate = pessoaDb.get();
			
			if(pessoaJuridica != null && StringUtils.isNotBlank(pessoaJuridica.getCnpj())) {
				if(Util.validaCNPJ(pessoaJuridica.getCnpj())) {
					pessoaUpdate.setId(pessoaJuridica.getId());
					pessoaUpdate.setNome(pessoaJuridica.getNome());
					pessoaUpdate.setCnpj(Util.removeNaoNumericos(pessoaJuridica.getCnpj()));
					
					try {
						return pessoaJuridicaRepository.save(pessoaUpdate);
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
			throw new PessoaException("PessoaJuridica não encontrada com o ID: " + pessoaJuridica.getId());
		}
	}
}
