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

import com.gr.contacorrente.model.Pessoa;
import com.gr.contacorrente.model.PessoaJuridica;
import com.gr.contacorrente.service.PessoaJuridicaService;
import com.gr.contacorrente.util.RequestError;

import io.swagger.annotations.ApiOperation;

/**
 * Controller para PessoaJuridica
 * @author gustavogrossmann
 *
 */
@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins="*")
public class PessoaJuridicaController {

	@Autowired private PessoaJuridicaService pessoaJuridicaService;
	
	@ApiOperation(value = "Buscar pessoas jurídicas")
	@GetMapping("/pessoasjuridicas")
	public ResponseEntity<List<PessoaJuridica>> getAll(){
		return ResponseEntity.ok().body(pessoaJuridicaService.getAll());
	}
	
	@ApiOperation(value = "Buscar pessoa física")
	@GetMapping("/pessoasjuridicas/{id}")
	public ResponseEntity<PessoaJuridica> getPessoaJuridica(@PathVariable long id){
		return ResponseEntity.ok().body(pessoaJuridicaService.findById(id));
	}
	
	@ApiOperation(value = "Cadastrar pessoa jurídica")
	@PostMapping("/pessoasjuridicas")
	public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody PessoaJuridica pessoa){
		return ResponseEntity.ok().body(pessoaJuridicaService.cadastrarCliente(pessoa));
	}
	
	@ApiOperation(value = "Atualizar cadastro de pessoa jurídica")
	@PutMapping("/pessoasjuridicas/{id}")
	public ResponseEntity<Object> atualizarCliente(@PathVariable long id, @RequestBody PessoaJuridica pessoa){
		try {
			pessoa.setId(id);
			return ResponseEntity.ok().body(pessoaJuridicaService.atualizarCliente(pessoa));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestError(e.getMessage()));
		}
	}
}
