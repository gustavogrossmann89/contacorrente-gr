package com.gr.contacorrente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gr.contacorrente.model.ContaCorrente;
import com.gr.contacorrente.model.TipoTransacao;
import com.gr.contacorrente.model.Transacao;
import com.gr.contacorrente.service.ContaCorrenteService;
import com.gr.contacorrente.util.RequestError;

import io.swagger.annotations.ApiOperation;

/**
 * Controller para ContaCorrente
 * @author gustavogrossmann
 *
 */
@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins="*")
public class ContaCorrenteController {

	@Autowired private ContaCorrenteService contaCorrenteService;
	
	@ApiOperation(value = "Buscar contas correntes")
	@GetMapping("/contascorrentes")
	public ResponseEntity<List<ContaCorrente>> getAll(){
		return ResponseEntity.ok().body(contaCorrenteService.getAll());
	}
	
	@ApiOperation(value = "Buscar conta corrente")
	@GetMapping("/contascorrentes/{id}")
	public ResponseEntity<ContaCorrente> getContaCorrente(@PathVariable long id){
		return ResponseEntity.ok().body(contaCorrenteService.findById(id));
	}
	
	@ApiOperation(value = "Consultar saldo de uma conta corrente")
	@GetMapping("/contascorrentes/{id}/saldo")
	public ResponseEntity<Object> getSaldo(@PathVariable long id){
		try {
			return ResponseEntity.ok().body(contaCorrenteService.findById(id).getSaldo());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestError(e.getMessage()));
		}
	}
	
	@ApiOperation(value = "Consultar extrato de uma conta corrente")
	@GetMapping("/contascorrentes/{id}/extrato")
	public ResponseEntity<List<Transacao>> getExtrato(@PathVariable long id){
		return ResponseEntity.ok().body(contaCorrenteService.findTransacoesByConta(id));
	}
	
	@ApiOperation(value = "Realizar uma operação de saque em conta corrente")
	@PostMapping("/contascorrentes/{id}/saque")
	public ResponseEntity<Object> realizarSaque(@PathVariable long id, @RequestBody Transacao transacao){
		try {
			transacao.setTipo(TipoTransacao.SAQUE);
			return ResponseEntity.ok().body(contaCorrenteService.realizarOperacao(transacao, id));
		} catch (Exception e) { 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestError(e.getMessage()));
		}
			
	}
	
	@ApiOperation(value = "Realizar uma operação de deposito em conta corrente")
	@PostMapping("/contascorrentes/{id}/deposito")
	public ResponseEntity<Object> realizarDeposito(@PathVariable long id, @RequestBody Transacao transacao){
		try { 
			transacao.setTipo(TipoTransacao.DEPOSITO);
			return ResponseEntity.ok().body(contaCorrenteService.realizarOperacao(transacao, id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestError(e.getMessage()));
		}
	}
}
