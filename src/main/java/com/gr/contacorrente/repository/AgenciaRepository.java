package com.gr.contacorrente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gr.contacorrente.model.Agencia;
import com.gr.contacorrente.model.ContaCorrente;

public interface AgenciaRepository extends JpaRepository<Agencia, Long>{

	public Agencia findByNumero(int numero);
	
	@Query("SELECT c FROM ContaCorrente c WHERE c.agencia.id = ?1")
	public List<ContaCorrente> findContasByAgencia(long agenciaId);
}
