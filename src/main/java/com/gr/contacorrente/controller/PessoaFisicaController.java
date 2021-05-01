package com.gr.contacorrente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gr.contacorrente.model.PessoaFisica;
import com.gr.contacorrente.service.PessoaFisicaService;
import com.gr.contacorrente.util.RequestError;

import io.swagger.annotations.ApiOperation;

/**
 * Controller para PessoaFisica
 * @author gustavogrossmann
 *
 */
@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins="*")
public class PessoaFisicaController {

	@Autowired private PessoaFisicaService pessoaFisicaService;
	
	@ApiOperation(value = "Buscar pessoas físicas")
	@GetMapping("/pessoasfisicas")
	public ResponseEntity<List<PessoaFisica>> getAll(){
		return ResponseEntity.ok().body(pessoaFisicaService.getAll());
	}
	
	@ApiOperation(value = "Buscar pessoa física")
	@GetMapping("/pessoasfisicas/{id}")
	public ResponseEntity<PessoaFisica> getPessoaFisica(@PathVariable long id){
		return ResponseEntity.ok().body(pessoaFisicaService.findById(id));
	}
	
	@ApiOperation(value = "Cadastrar pessoa física")
	@PostMapping("/pessoasfisicas")
	public ResponseEntity<Object> cadastrarCliente(@RequestBody PessoaFisica pessoa){
		try {
			return ResponseEntity.ok().body(pessoaFisicaService.cadastrarCliente(pessoa));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestError(e.getMessage()));
		}
	}
	
	@ApiOperation(value = "Atualizar cadastro de pessoa física")
	@PutMapping("/pessoasfisicas/{id}")
	public ResponseEntity<Object> atualizarCliente(@PathVariable long id, @RequestBody PessoaFisica pessoa){
		try {
			pessoa.setId(id);
			return ResponseEntity.ok().body(pessoaFisicaService.atualizarCliente(pessoa));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestError(e.getMessage()));
		}
	}
}
