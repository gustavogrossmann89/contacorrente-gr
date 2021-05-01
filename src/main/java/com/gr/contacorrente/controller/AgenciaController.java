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

import com.gr.contacorrente.model.Agencia;
import com.gr.contacorrente.model.ContaCorrente;
import com.gr.contacorrente.service.AgenciaService;
import com.gr.contacorrente.service.ContaCorrenteService;
import com.gr.contacorrente.util.RequestError;

import io.swagger.annotations.ApiOperation;

/**
 * Controller para Agencia
 * @author gustavogrossmann
 *
 */
@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins="*")
public class AgenciaController {

	@Autowired private AgenciaService agenciaService;
	@Autowired private ContaCorrenteService contaCorrenteService;
	
	@ApiOperation(value = "Buscar agências")
	@GetMapping("/agencias")
	public ResponseEntity<List<Agencia>> getAll(){
		return ResponseEntity.ok().body(agenciaService.getAll());
	}
	
	@ApiOperation(value = "Buscar agência")
	@GetMapping("/agencias/{id}")
	public ResponseEntity<Agencia> getAgencia(@PathVariable long id){
		return ResponseEntity.ok().body(agenciaService.findById(id));
	}
	
	@ApiOperation(value = "Cadastrar agencia")
	@PostMapping("/agencias")
	public ResponseEntity<Object> cadastrarAgencia(@RequestBody Agencia agencia){
		try {
			return ResponseEntity.ok().body(agenciaService.cadastrarAgencia(agencia));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestError(e.getMessage()));
		}
	}
	
	@ApiOperation(value = "Buscar contas correntes de uma agencia")
	@GetMapping("/agencias/{id}/contascorrentes")
	public ResponseEntity<List<ContaCorrente>> getContasCorrentes(@PathVariable long id){
		return ResponseEntity.ok().body(agenciaService.findContasByAgencia(id));
	}
	
	@ApiOperation(value = "Cadastrar conta corrente em determinada agencia, para uma pessoa já cadastrada")
	@PostMapping("/agencias/{id}/contascorrentes/{pessoaId}")
	public ResponseEntity<Object> cadastrarContaCorrente(@PathVariable long id, @PathVariable long pessoaId, @RequestBody ContaCorrente conta){
		try { 
			return ResponseEntity.ok().body(contaCorrenteService.cadastrarContaCorrente(id, pessoaId, conta));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestError(e.getMessage()));
		}
	}
}
