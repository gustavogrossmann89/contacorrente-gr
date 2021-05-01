package com.gr.contacorrente.service;

import java.util.List;

import com.gr.contacorrente.model.ContaCorrente;
import com.gr.contacorrente.model.Transacao;

public interface ContaCorrenteService {
	
	public List<ContaCorrente> getAll();
	public ContaCorrente findById(long id);
	public List<Transacao> findTransacoesByConta(long contaId);
	public ContaCorrente cadastrarContaCorrente(long agenciaId, long pessoaId, ContaCorrente conta);
	public Transacao realizarOperacao(Transacao transacao, long contaId);
}
