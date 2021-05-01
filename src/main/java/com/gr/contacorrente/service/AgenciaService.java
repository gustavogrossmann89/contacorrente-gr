package com.gr.contacorrente.service;

import java.util.List;

import com.gr.contacorrente.model.Agencia;
import com.gr.contacorrente.model.ContaCorrente;

public interface AgenciaService {
	
	public List<Agencia> getAll();
	public Agencia findById(long id);
	public Agencia cadastrarAgencia(Agencia agencia);
	public List<ContaCorrente> findContasByAgencia(long agenciaId);
}
