package com.gr.contacorrente.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gr.contacorrente.exception.AgenciaException;
import com.gr.contacorrente.model.Agencia;
import com.gr.contacorrente.model.ContaCorrente;
import com.gr.contacorrente.repository.AgenciaRepository;
import com.gr.contacorrente.service.AgenciaService;

/**
 * Service para Agencia
 * @author gustavogrossmann
 *
 */
@Service
@Transactional
public class AgenciaServiceImpl implements AgenciaService{

	@Autowired private AgenciaRepository agenciaRepository;

	@Override
	public List<Agencia> getAll() {
		return agenciaRepository.findAll();
	}
	
	@Override
	public Agencia findById(long id) {
		Optional<Agencia> agencia = agenciaRepository.findById(id);
		if(agencia.isPresent()) {
			return agencia.get();
		}
		throw new AgenciaException("Agência não encontrada com o ID: " + id);
	}
	
	/**
	 * Cadastro de Agência
	 * Não se pode haver duas agências com o mesmo número
	 */
	@Override
	public Agencia cadastrarAgencia(Agencia agencia) {
		Agencia a = agenciaRepository.findByNumero(agencia.getNumero());
		if(a == null) {
			try {
				return agenciaRepository.save(agencia);
			} catch (Exception e) {
				throw new AgenciaException("Não foi possível cadastrar a agencia: " + e.getMessage());		
			}
		} else {
			throw new AgenciaException("Já existe uma agência com o número informado");
		}
	}
	
	public List<ContaCorrente> findContasByAgencia(long agenciaId){
		return agenciaRepository.findContasByAgencia(agenciaId);
	}
}
