package com.gr.contacorrente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gr.contacorrente.model.ContaCorrente;
import com.gr.contacorrente.model.Pessoa;
import com.gr.contacorrente.model.Transacao;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long>{

	public ContaCorrente findByNumero(int numero);
	public ContaCorrente findByPessoa(Pessoa pessoa);
	
	@Query("SELECT t FROM Transacao t WHERE t.conta.id = ?1")
	public List<Transacao> findTransacoesByConta(long contaId);
}
