package com.gr.contacorrente.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gr.contacorrente.exception.ContaCorrenteException;
import com.gr.contacorrente.exception.TransacaoException;
import com.gr.contacorrente.model.Agencia;
import com.gr.contacorrente.model.ContaCorrente;
import com.gr.contacorrente.model.Pessoa;
import com.gr.contacorrente.model.TipoTransacao;
import com.gr.contacorrente.model.Transacao;
import com.gr.contacorrente.repository.AgenciaRepository;
import com.gr.contacorrente.repository.ContaCorrenteRepository;
import com.gr.contacorrente.repository.PessoaRepository;
import com.gr.contacorrente.repository.TransacaoRepository;
import com.gr.contacorrente.service.ContaCorrenteService;
import com.gr.contacorrente.util.MathUtil;

/**
 * Service para Conta Corrente
 * @author gustavogrossmann
 *
 */
@Service
@Transactional
public class ContaCorrenteServiceImpl implements ContaCorrenteService{

	@Autowired private ContaCorrenteRepository contaCorrenteRepository;
	@Autowired private AgenciaRepository agenciaRepository;
	@Autowired private PessoaRepository pessoaRepository;
	@Autowired private TransacaoRepository transacaoRepository;

	@Override
	public List<ContaCorrente> getAll() {
		return contaCorrenteRepository.findAll();
	}
	
	@Override
	public ContaCorrente findById(long id) {
		Optional<ContaCorrente> contaCorrente = contaCorrenteRepository.findById(id);
		if(contaCorrente.isPresent()) {
			return contaCorrente.get();
		}
		throw new ContaCorrenteException("ContaCorrente não encontrada com o ID: " + id);
	}
	
	/**
	 * Listagem de Transações, utilizada para o extrato de uma conta corrente
	 */
	public List<Transacao> findTransacoesByConta(long contaId){
		return contaCorrenteRepository.findTransacoesByConta(contaId);
	}
	
	/**
	 * Cadastro de Conta Corrente
	 * Pré-requisitos: pessoa (PF ou PJ) e agência já cadastradas 
	 * Não se pode haver duas contas correntes com o mesmo número 
	 */
	@Override
	public ContaCorrente cadastrarContaCorrente(long agenciaId, long pessoaId, ContaCorrente contaCorrente) {
		Optional<Agencia> agenciaDb = agenciaRepository.findById(agenciaId);
		Agencia agencia = null;
		if(agenciaDb.isPresent()) {
			agencia = agenciaDb.get();
			contaCorrente.setAgencia(agencia);
		} else {
			throw new ContaCorrenteException("Agência não encontrada para cadastro da conta corrente");
		}
		
		ContaCorrente c = contaCorrenteRepository.findByNumero(contaCorrente.getNumero());
		if(c != null) {
			throw new ContaCorrenteException("Já existe uma conta corrente com o número informado");
		}
		
		Optional<Pessoa> pessoaDb = pessoaRepository.findById(pessoaId);
		Pessoa pessoa = null;
		if(pessoaDb.isPresent()) {
			pessoa = pessoaDb.get();
			contaCorrente.setPessoa(pessoa);
			
			c = contaCorrenteRepository.findByPessoa(pessoa);
			if(c != null) {
				throw new ContaCorrenteException("Já existe uma conta corrente para a pessoa informada");
			}
		} else {
			throw new ContaCorrenteException("Cliente não encontrado. Por favor, cadastre-o antes da conta corrente");
		}
		
		try {
			return contaCorrenteRepository.save(contaCorrente);
		} catch (Exception e) {
			throw new ContaCorrenteException("Não foi possível cadastrar a conta corrente: " + e.getMessage());		
		}
	}
	
	/**
	 * Operação na Conta Corrente
	 * Pré-requisitos: para SAQUE, a conta deve possuir saldo suficiente. Para DEPOSITO, não existem restrições referentes ao saldo  
	 */
	@Override
	public Transacao realizarOperacao(Transacao transacao, long contaId) {
		Optional<ContaCorrente> contaCorrenteDb = contaCorrenteRepository.findById(contaId);
		ContaCorrente conta = null;
		if(contaCorrenteDb.isPresent()) {
			conta = contaCorrenteDb.get();
			transacao.setConta(conta);
		} else {
			throw new TransacaoException("Conta corrente não encontrada para realização da operação");
		}
		
		if(transacao.getTipo().equals(TipoTransacao.SAQUE)){
			//Se for do tipo do saque, o saldo da conta não pode ser menor ou igual zero E o valor do saque tem de ser suficiente para não deixar a conta negativa
			if(MathUtil.menorIgualZero(conta.getSaldo()) || MathUtil.menorQueZero(conta.getSaldo().subtract(transacao.getValor()))) {
				throw new TransacaoException("Conta corrente não possui saldo suficiente para saque");
			} else {
				transacaoRepository.save(transacao);
				conta.setSaldo(conta.getSaldo().subtract(transacao.getValor()));
				contaCorrenteRepository.save(conta);
				
				return transacao;
			}
		} else if (transacao.getTipo().equals(TipoTransacao.DEPOSITO)){
			//Depósitos são sempre permitidos, independente do saldo da conta
			try {
				transacaoRepository.save(transacao);
				conta.setSaldo(conta.getSaldo().add(transacao.getValor()));
				contaCorrenteRepository.save(conta);
				
				return transacao;
			} catch (Exception e) {
				throw new TransacaoException("Sua transação não pôde ser realizada");
			}
		} else {
			throw new TransacaoException("Sua transação não pôde ser realizada");
		}
	}
}
